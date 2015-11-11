import java.net.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.*;
public class FrontEnd {
	static DynamicDataDemo TCPchart;
	static DynamicDataDemo UDPchart; 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	float bytesReceived = 0;
	float bytesReceived2 = 0;
    /** The time series data. */
    private static TimeSeries series;

    /** The most recent value added. */
    private double lastValue = 100.0;
   
    public FrontEnd() {
    	   
            this.bytesReceived = 0;
            this.bytesReceived2 = 0;
            
    }
    
    public float getBytesReceived(int in, String id) {
        try{
        System.out.println("RECEIVED "+ in+" bytes from "+ id);
        FrontEnd.TCPchart.updateParam(in);
        }catch(Exception e){}
        
        return this.bytesReceived;
    }
    
    public float getBytesReceived2(int in, String id) {
        try{
        System.out.println("RECEIVED "+ in+" bytes from "+ id);
        FrontEnd.UDPchart.updateParam(in);
        }catch(Exception e){}
        
        return this.bytesReceived2;
    }
    
    
    public static void createGUI(){
    	TCPchart = new DynamicDataDemo("TCP Data","Time","Bytes","TCP Data Received");
    	UDPchart = new DynamicDataDemo("UDP Data", "Time", "Bytes", "UDP Data Received");
    	JFrame mainframe = new JFrame("Diagnostics Window");
    	JPanel defaultpanel = new JPanel();
    	
    	Thread t = new Thread(TCPchart);
    	t.start();
    	Thread t2 = new Thread(UDPchart);
    	t2.start();
    	
    	defaultpanel.add(TCPchart.content);
    	defaultpanel.add(UDPchart.content);
    	defaultpanel.setPreferredSize(new Dimension(800,600));
    	mainframe.setContentPane(defaultpanel);
    	mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	mainframe.pack();
    	mainframe.setVisible(true);
    }
    
}