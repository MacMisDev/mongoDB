package pl.ug.edu.inf.misc;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.springframework.stereotype.Component;


@Component("aggregation")
public class AggregationImpl implements Aggregation{

    @Override
    public AggregationOutput iloscPrzestepstwWDanejStrefie(DBCollection dbCollection) {
        DBObject match = new BasicDBObject("$match", new BasicDBObject("total", new BasicDBObject("$gte", 700)));
        DBObject sort = new BasicDBObject("$sort", new BasicDBObject("total", -1));
        DBObject groupFields = new BasicDBObject("_id", "$Block");
        groupFields.put("total", new BasicDBObject("$sum", 1));
        DBObject group = new BasicDBObject("$group", groupFields);
        return dbCollection.aggregate(group, sort, match);
    }

    @Override
    public AggregationOutput iloscPrzestepstwWDanymRoku(DBCollection dbCollection) {
        DBObject sort = new BasicDBObject("$sort", new BasicDBObject("total", -1));
        DBObject groupFields = new BasicDBObject("_id", "$Year");
        groupFields.put("total", new BasicDBObject("$sum", 1));
        DBObject group = new BasicDBObject("$group", groupFields);
        return dbCollection.aggregate(group, sort);
    }

    @Override
    public AggregationOutput iloscAresztowan(DBCollection dbCollection) {
        DBObject match = new BasicDBObject("$match", new BasicDBObject("Arrest", "true"));
        DBObject sort = new BasicDBObject("$sort", new BasicDBObject("total", -1));
        DBObject groupFields = new BasicDBObject("_id", "$Year");
        groupFields.put("total", new BasicDBObject("$sum", 1));
        DBObject group = new BasicDBObject("$group", groupFields);
        return dbCollection.aggregate(match, group, sort);
    }

    @Override
    public AggregationOutput iloscKradziezyWDanymMiejscu(DBCollection dbCollection) {
        DBObject matchGte = new BasicDBObject("$match", new BasicDBObject("total", new BasicDBObject("$gte", 500)));
        DBObject matchType = new BasicDBObject("$match", new BasicDBObject("Primary Type", "THEFT"));
        DBObject sort = new BasicDBObject("$sort", new BasicDBObject("total", -1));
        DBObject groupFields = new BasicDBObject("_id", "$Location Description");
        groupFields.put("total", new BasicDBObject("$sum", 1));
        DBObject group = new BasicDBObject("$group", groupFields);
        return dbCollection.aggregate(matchType, group, sort, matchGte);
    }
}
