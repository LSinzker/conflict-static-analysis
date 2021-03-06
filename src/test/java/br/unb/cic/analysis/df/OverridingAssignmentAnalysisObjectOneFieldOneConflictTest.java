package br.unb.cic.analysis.df;

import br.unb.cic.analysis.AbstractMergeConflictDefinition;
import br.unb.cic.analysis.SootWrapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import soot.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OverridingAssignmentAnalysisObjectOneFieldOneConflictTest {

    private OverridingAssignmentFieldsRefAnalysis analysis;

    @Before
    public void configure() {
        G.reset();
        Collector.instance().clear();

        AbstractMergeConflictDefinition definition = new AbstractMergeConflictDefinition() {
            @Override
            protected Map<String, List<Integer>> sourceDefinitions() {
                Map<String, List<Integer>> res = new HashMap<>();
                List<Integer> lines = new ArrayList<>();
                lines.add(10);    //left
                lines.add(13);    //left
                res.put("br.unb.cic.analysis.samples.OverridingAssignmentObjectOneFieldOneConflictSample", lines);
                return res;
            }

            @Override
            protected Map<String, List<Integer>> sinkDefinitions() {
                Map<String, List<Integer>> res = new HashMap<>();
                List<Integer> lines = new ArrayList<>();
                lines.add(11);    //right
                lines.add(14);    //right
                res.put("br.unb.cic.analysis.samples.OverridingAssignmentObjectOneFieldOneConflictSample", lines);
                return res;
            }
        };

        PackManager.v().getPack("jtp").add(
                new Transform("jtp.oneConflict", new BodyTransformer() {
                    @Override
                    protected void internalTransform(Body body, String phaseName, Map<String, String> options) {
                        analysis = new OverridingAssignmentFieldsRefAnalysis(body, definition);
                    }
                }));
        String cp = "target/test-classes";
        String targetClass = "br.unb.cic.analysis.samples.OverridingAssignmentObjectOneFieldOneConflictSample";
        PhaseOptions.v().setPhaseOption("jb", "use-original-names:true");

        SootWrapper.builder().withClassPath(cp).addClass(targetClass).build().execute();
    }

    @Test
    public void testDataFlowAnalysisExpectingOneConflict() {
        Assert.assertEquals(1, analysis.getConflicts().size());
    }
}
