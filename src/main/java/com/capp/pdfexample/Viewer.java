package com.capp.pdfexample;

import java.io.ByteArrayInputStream;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

/**
 *
 * @author mucahit.yilmaz
 */
public class Viewer {

    public static void view(byte[] bytes) {
        SwingController controller = new SwingController();

        SwingViewBuilder factory = new SwingViewBuilder(controller);

        JPanel viewerComponentPanel = factory.buildViewerPanel();

        ComponentKeyBinding.install(controller, viewerComponentPanel);

        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()));

        JFrame window = new JFrame("Using the Viewer Component");
        window.getContentPane().add(viewerComponentPanel);
        window.pack();
        window.setVisible(true);

        controller.openDocument(new ByteArrayInputStream(bytes), "", null);
    }
}
