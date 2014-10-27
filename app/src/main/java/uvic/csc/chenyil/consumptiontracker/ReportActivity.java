package uvic.csc.chenyil.consumptiontracker;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.WindowManager;

import com.androidplot.pie.PieChart;
import com.androidplot.pie.PieRenderer;
import com.androidplot.pie.Segment;
import com.androidplot.pie.SegmentFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import uvic.csc.chenyil.consumptiontracker.database.Food;
import uvic.csc.chenyil.consumptiontracker.database.LimitsGoals;
import uvic.csc.chenyil.consumptiontracker.database.Nutrient;
import uvic.csc.chenyil.consumptiontracker.database.Profile;
import uvic.csc.chenyil.consumptiontracker.database.RelationFoodNutrients;
import uvic.csc.chenyil.consumptiontracker.database.RelationProfileFood;

public class ReportActivity extends Activity {
    private XYPlot plot;
    private PieChart pieChart;

    private Profile profile=new Profile();
    private ArrayList<LimitsGoals> limitsGoals;
    private ArrayList<Nutrient> nutrients;
    private ArrayList<Food> foods;
    private ArrayList<RelationProfileFood> rpfs;
    private ArrayList<RelationFoodNutrients> rfns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        //setTitle
        getActionBar().setTitle("Report");

        // fun little snippet that prevents users from taking screenshots
        // on ICS+ devices :-)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);

        createLineChart();
        createPirchart();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.report, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createSampleData(){

    }
    //create a line chart
    private void createLineChart(){
        // initialize our XYPlot reference:
        plot = (XYPlot) findViewById(R.id.mySimpleXYPlot);

        // Create a couple arrays of y-values to plot:
        Number[] series1Numbers = {1000, 1200, 2800, 1400, 1400, 2000};
        //Number[] series2Numbers = {4, 6, 3, 8, 2, 10};
        Number[] limitNumbers = {1500,1500,1500,1500,1500,1500};

        Number[] days = {1,2,3,4,5,6};

        // Turn the above arrays into XYSeries':
        XYSeries series1 = new SimpleXYSeries(
                Arrays.asList(series1Numbers),          // SimpleXYSeries takes a List so turn our array into a List
                //Arrays.asList(days),
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, // Y_VALS_ONLY means use the element index as the x value
                "Calories");                             // Set the display title of the series

        // same as above
        XYSeries series2 = new SimpleXYSeries(Arrays.asList(limitNumbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Limit");

        // Create a formatter to use for drawing a series using LineAndPointRenderer
        // and configure it from xml:
        LineAndPointFormatter series1Format = new LineAndPointFormatter();
        series1Format.setPointLabelFormatter(new PointLabelFormatter());
        series1Format.configure(getApplicationContext(),
                R.xml.line_point_formatter_with_plf1);

        // add a new series' to the xyplot:
        plot.addSeries(series1, series1Format);

        // same as above:
        LineAndPointFormatter series2Format = new LineAndPointFormatter();
        series2Format.setPointLabelFormatter(new PointLabelFormatter());
        series2Format.configure(getApplicationContext(),
                R.xml.line_point_formatter_with_plf2);
        plot.addSeries(series2, series2Format);

        // reduce the number of range labels
        plot.setTicksPerRangeLabel(3);
        plot.getGraphWidget().setDomainLabelOrientation(-45);
    }

    private void createPirchart()
    {
        // initialize our XYPlot reference:
        pieChart = (PieChart) findViewById(R.id.myPiePlot);

        pieChart.getBackgroundPaint().setColor(Color.WHITE);

        Segment seg0 = new Segment("Bread", 50);
        Segment seg1 = new Segment("Meat", 40);
        Segment seg2 = new Segment("Snack", 10);

        pieChart.addSeries(seg0, new SegmentFormatter(Color.rgb(106, 168, 79), Color.BLACK,Color.BLACK, Color.BLACK));
        pieChart.addSeries(seg1, new SegmentFormatter(Color.rgb(255, 0, 0), Color.BLACK,Color.BLACK, Color.BLACK));
        pieChart.addSeries(seg2, new SegmentFormatter(Color.rgb(255, 153, 0), Color.BLACK,Color.BLACK, Color.BLACK));
        PieRenderer pieRenderer = pieChart.getRenderer(PieRenderer.class);
        pieRenderer.setDonutSize((float) 0 / 100,   PieRenderer.DonutMode.PERCENT);
    }
}
