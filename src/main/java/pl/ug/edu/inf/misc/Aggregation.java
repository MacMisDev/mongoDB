package pl.ug.edu.inf.misc;


import com.mongodb.AggregationOutput;
import com.mongodb.DBCollection;

public interface Aggregation {
    public AggregationOutput iloscPrzestepstwWDanejStrefie(DBCollection dbCollection);
    public AggregationOutput iloscPrzestepstwWDanymRoku(DBCollection dbCollection);
    public AggregationOutput iloscAresztowan(DBCollection dbCollection);
    public AggregationOutput iloscKradziezyWDanymMiejscu(DBCollection dbCollection);
}
