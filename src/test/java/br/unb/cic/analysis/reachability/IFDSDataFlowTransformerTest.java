package br.unb.cic.analysis.reachability;

import br.unb.cic.analysis.SootWrapper;
import br.unb.cic.analysis.samples.IFDSDataFlowSample;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import soot.*;
import soot.options.Options;

import java.util.ArrayList;
import java.util.List;

public class IFDSDataFlowTransformerTest {

    private IFDSDataFlowTransformer transformer;

    @Before
    public void configure() {

        String cp = "target/test-classes";
        String targetClass = "br.unb.cic.analysis.samples.IFDSDataFlowSample";

        // Set Soot's internal classpath
        Options.v().set_soot_classpath(cp);

        // Enable whole-program mode
        Options.v().set_whole_program(true);
        Options.v().set_app(true);

        // Call-graph options
        Options.v().setPhaseOption("cg", "safe-newinstance:true");
        Options.v().setPhaseOption("cg.cha","enabled:false");

        // Enable SPARK call-graph construction
        Options.v().setPhaseOption("cg.spark","enabled:true");
        Options.v().setPhaseOption("cg.spark","verbose:true");
        Options.v().setPhaseOption("cg.spark","on-fly-cg:true");

        Options.v().set_allow_phantom_refs(true);
        Options.v().set_output_format(Options.output_format_class);

        // Set the main class of the application to be analysed
        Options.v().set_main_class("br.unb.cic.analysis.samples.IFDSDataFlowSample");

        Scene.v().loadNecessaryClasses();

        // Load the main class
        SootClass c = Scene.v().loadClass("IFDSDataFlowSample", SootClass.BODIES);
        c.setApplicationClass();

        // Load the "main" method of the main class and set it as a Soot entry point
        SootMethod entryPoint = c.getMethodByName("main");

        List<SootMethod> entryPoints = new ArrayList<SootMethod>();
        entryPoints.add(entryPoint);
        Scene.v().setEntryPoints(entryPoints);


        Options.v().set_output_dir("sootOutput");

        PackManager.v().getPack("wjtp").add(new Transform("wjtp.herosifds", new IFDSDataFlowTransformer()));

        soot.Main.main(new String[] {"br.unb.cic.analysis.samples.IFDSDataFlowSample"});
    }

    @Test
    public void testIFDS() {

    }
}