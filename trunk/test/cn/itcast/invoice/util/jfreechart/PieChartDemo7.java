package cn.itcast.invoice.util.jfreechart;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
/**
 * this class is a demo class for testing
 *
 */
public class PieChartDemo7 extends ApplicationFrame {
	private PieChartDemo7(String paramString) {
		super(paramString);
		setContentPane(createDemoPanel());
	}

	private PieDataset createDataset() {
		DefaultPieDataset localDefaultPieDataset = new DefaultPieDataset();
		localDefaultPieDataset.setValue("SectionXX", 1.0d);
		localDefaultPieDataset.setValue("SectionYY", 2.0d);
		localDefaultPieDataset.setValue("SectionZZ", 3.0d);
		localDefaultPieDataset.setValue("SectionAA", 4.0d);
		return localDefaultPieDataset;
	}

	public JPanel createDemoPanel() {
		PieDataset localPieDataset = createDataset();
		JFreeChart localJFreeChart = ChartFactory.createPieChart(
				"Pie Chart Demo 7", localPieDataset, false, true, false);
		PiePlot localPiePlot = (PiePlot) localJFreeChart.getPlot();
		localPiePlot.setCircular(true);
		localPiePlot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0} = {2}", NumberFormat.getNumberInstance(), NumberFormat
						.getPercentInstance()));
		localPiePlot.setNoDataMessage("No data available");
		ChartPanel localChartPanel = new ChartPanel(localJFreeChart);
		localChartPanel.setPreferredSize(new Dimension(500, 270));
		Rotator localRotator = new Rotator(localPiePlot);
		localRotator.start();
		return localChartPanel;
	}


	static class Rotator extends Timer implements ActionListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private PiePlot plot;
		private int angle = 270;

		Rotator(PiePlot paramPiePlot) {
			super(-1000,null);
			this.plot = paramPiePlot;
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent paramActionEvent) {
			this.plot.setStartAngle(this.angle);
			this.angle += 1;
			if (this.angle == 360)
				this.angle = 0;
		}
	}
}