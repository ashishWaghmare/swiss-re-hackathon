package in.co.waghmare;

import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.TradeoffAnalytics;
import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.model.Dilemma;
import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.model.Option;
import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.model.Problem;
import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.model.column.Column;
import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.model.column.Column.Goal;
import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.model.column.NumericColumn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by ashishw on 17/1/16.
 */

public class TradeoffAnalyticsSample {
    public static void main(String[] args) {
        TradeoffAnalytics service = new TradeoffAnalytics();
        service.setUsernameAndPassword("9350a1d8-340e-4a04-8f7a-46b30017ab10", "3Sem4SNJtXv1");

        Problem problem = new Problem("phone");

        String price = "price";
        String ram = "ram";
        String screen = "screen";

        // Define the objectives
        List<Column> columns = new ArrayList<Column>();
        problem.setColumns(columns);

        columns.add(new NumericColumn().withRange(0, 100).withKey(price).withGoal(Goal.MIN)
                .withObjective(true));
        columns.add(new NumericColumn().withKey(screen).withGoal(Goal.MAX).withObjective(true));
        columns.add(new NumericColumn().withKey(ram).withGoal(Goal.MAX));

        // Define the options to choose
        List<Option> options = new ArrayList<Option>();
        problem.setOptions(options);

        HashMap<String, Object> galaxySpecs = new HashMap<String, Object>();
        galaxySpecs.put(price, 50);
        galaxySpecs.put(ram, 45);
        galaxySpecs.put(screen, 5);
        options.add(new Option("1", "Galaxy S4").withValues(galaxySpecs));

        HashMap<String, Object> iphoneSpecs = new HashMap<String, Object>();
        iphoneSpecs.put(price, 99);
        iphoneSpecs.put(ram, 40);
        iphoneSpecs.put(screen, 4);
        options.add(new Option("2", "iPhone 5").withValues(iphoneSpecs));

        HashMap<String, Object> optimusSpecs = new HashMap<String, Object>();
        optimusSpecs.put(price, 10);
        optimusSpecs.put(ram, 300);
        optimusSpecs.put(screen, 5);
        options.add(new Option("3", "LG Optimus G").withValues(optimusSpecs));

        // Call the service and get the resolution
        Dilemma dilemma = service.dilemmas(problem);

        System.out.println(dilemma);
    }
}
