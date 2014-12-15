import GPS.GPGLL;
import GPS.Parser;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserFactory;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GoogleMaps {

    public static final int MIN_ZOOM = 0;
    public static final int MAX_ZOOM = 21;

    /**
     * In map.html file default zoom value is set to 4.
     */
    private static int zoomValue = 4;

    public static void run() {
        final Browser browser = BrowserFactory.create();

        JButton zoomInButton = new JButton("Zoom In");
        zoomInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (zoomValue < MAX_ZOOM) {
                    browser.executeJavaScript("map.setZoom(" + ++zoomValue + ")");
                }
            }
        });

        JButton zoomOutButton = new JButton("Zoom Out");
        zoomOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (zoomValue > MIN_ZOOM) {
                    browser.executeJavaScript("map.setZoom(" + --zoomValue + ")");
                }
            }
        });
        
        
        JButton connectButton = new JButton(">>>connect<<<");
        connectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {    

            	Parser parser = new Parser();
            	List<GPGLL> gpgll = parser.parser();
            	for (GPGLL gpgllData : gpgll) {
            		String w = "['GPGLL', "+gpgllData.latitude/100.0+", "+gpgllData.longitude/100.0+"]";
            		String ww = "['<h3>Received Time</h3>" +
								"<p>"+ gpgllData.time +"</p>']";
								
					System.out.println(w);
					System.out.println(ww);
								
					browser.executeJavaScript("markers.push("+ w +")");
            		browser.executeJavaScript("infoWindowContent.push("+ ww +")");	
            	}
            	browser.executeJavaScript("new initialize()");
            }
        });

        JPanel toolBar = new JPanel();
        toolBar.add(zoomInButton);
        toolBar.add(zoomOutButton);
        toolBar.add(connectButton);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.add(browser.getView().getComponent(), BorderLayout.CENTER);
        frame.add(toolBar, BorderLayout.NORTH);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        final String dir = System.getProperty("user.dir");
        browser.loadURL("file://" + dir + "/src/map.html");
        
    }
}
