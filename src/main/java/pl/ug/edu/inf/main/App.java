package pl.ug.edu.inf.main;


import com.mongodb.AggregationOutput;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import pl.ug.edu.inf.config.SpringMongoConfig;
import pl.ug.edu.inf.misc.Aggregation;

public class App {

    public static void main(String[] args){

        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperations = (MongoOperations) ctx.getBean("mongoTemplate");
        Aggregation aggregation = (Aggregation) ctx.getBean("aggregation");
        DBCollection police = mongoOperations.getCollection("police");

        Long start = System.currentTimeMillis();
        //AggregationOutput aggregationOutput = aggregation.iloscPrzestepstwWDanejStrefie(police);
        //AggregationOutput aggregationOutput = aggregation.iloscAresztowan(police);
        //AggregationOutput aggregationOutput = aggregation.iloscPrzestepstwWDanymRoku(police);
        AggregationOutput aggregationOutput = aggregation.iloscKradziezyWDanymMiejscu(police);
        for (DBObject result : aggregationOutput.results()){
            System.out.println(result);
        }

        int estimatedTime = (int)((System.currentTimeMillis() - start) / 1000);
        int minutes = estimatedTime / 60;
        int seconds = estimatedTime % 60;

        System.out.println("Minuty : " + minutes + ", sekundy : " + seconds);


    }


}
