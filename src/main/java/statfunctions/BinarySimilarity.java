package statfunctions;

import org.neo4j.procedure.Description;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.UserFunction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BinarySimilarity {


    @UserFunction

    @Description("Binary Similarity calculator example : statsfunctions.BinarySimilarity([2, 3], [1, 2], 'Jaccard' ) "
            + "given two collection vectors and a binary similarity function (Default to Cosine similarity), calculate Binary similarity")
    public Map<String, Double> BinarySimilarity(
            @Name("vector1") List<Object> vector1,
            @Name("vector2") List<Object> vector2,
            @Name(value = "function", defaultValue = "Cosine") String function) {

        Map<String, Double> results = new HashMap<>();

        double a = 0d;

        double b = 0d;

        double c = 0d;


        a = vector1.stream().filter(i -> vector2.contains(i)).distinct().count();
        b = vector2.stream().filter(j -> !vector1.contains(j)).distinct().count();
        c = vector1.stream().filter(i -> !vector2.contains(i)).distinct().count();

        switch (function) {
            case "Jaccard":
                results.put("Jaccard", jaccard(a, b, c));
                break;
            case "Cosine":
                results.put("Cosine", cosine(a, b, c));
                break;
            case "Euclid":
                results.put("Euclid", euclid(b, c));
                break;
            case "Manhattan":
                results.put("Manhattan", manhattan(b, c));
                break;
            case "Dice":
                results.put("Dice", dice(a, b, c));
                break;
            case "All":
                results.put("Jaccard", jaccard(a, b, c));
                results.put("Cosine", cosine(a, b, c));
                results.put("Euclid", euclid(b, c));
                results.put("Manhattan", manhattan(b, c));
                results.put("Dice", dice(a, b, c));

                default:
                    results.put("Cosine", cosine(a, b, c));
                    break;
        }
        return results;
    }

    @UserFunction

    @Description("given Numbers a, b, c calculate the jaccard similarity )")

    public double jaccard(@Name("a") Number a, @Name("b") Number b, @Name("c") Number c) {

        if (a.doubleValue() == 0) return 0;

        else return a.doubleValue() / (a.doubleValue() + b.doubleValue() + c.doubleValue());

    }

    @UserFunction

    @Description("given Numbers a, b, c  calculate the cosine similarity )")

    public double cosine(@Name("a") Number a, @Name("b") Number b, @Name("c") Number c) {

        Double denominator = Math.sqrt((a.doubleValue() + b.doubleValue()) * (a.doubleValue() + c.doubleValue()));
        if (denominator == 0) return denominator;

        else return a.doubleValue() / denominator;

    }

    @UserFunction

    @Description("given Numbers b, c  calculate the euclidean similarity )")

    public double euclid(@Name("b") Number b, @Name("c") Number c) {

        return Math.sqrt(b.doubleValue() + c.doubleValue());

    }

    @UserFunction

    @Description("given Numbers b, c calculate the manhattan similarity )")

    public double manhattan(@Name("b") Number b, @Name("c") Number c) {

        return b.doubleValue() + c.doubleValue();

    }

    @UserFunction

    @Description("given Numbers a, b, c calculate the dice similarity )")

    public double dice(@Name("a") Number a, @Name("b") Number b, @Name("c") Number c) {

        Double denominator = 2 * a.doubleValue() + b.doubleValue() + c.doubleValue();
        if (denominator == 0.0) return denominator;
        else return 2 * a.doubleValue() / denominator;

    }

}