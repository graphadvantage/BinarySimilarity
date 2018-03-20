package statfunctions;

import org.junit.Rule;
import org.junit.Test;
import org.neo4j.driver.v1.*;
import org.neo4j.harness.junit.Neo4jRule;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class BinarySimilarityTest
{


    // This rule starts a Neo4j instance
    @Rule
    public Neo4jRule neo4j = new Neo4jRule()

            // This is the function we want to test
            .withFunction( BinarySimilarity.class );

    @Test
    public void shouldUseCalculateBinaryCosine() throws Throwable
    {
        // This is in a try-block, to make sure we close the driver after the test
        try( Driver driver = GraphDatabase
                .driver( neo4j.boltURI() , Config.build().toConfig() ) )
        {
            // Given
            Session session = driver.session();

            // Test for base case default : Binary Cosine Similarity
            Value result1= session.run( "RETURN statfunctions.BinarySimilarity([1,2,3,4,4], [1,2,2,3,4,4]) AS result").single().get("result");

            assertThat( result1.get("Cosine").asDouble(), equalTo(1.0));


        }
    }

    @Test
    public void shouldCalculateAllFunctionsDifferentDataTypes() throws Throwable
    {
        // This is in a try-block, to make sure we close the driver after the test
        try( Driver driver = GraphDatabase
                .driver( neo4j.boltURI() , Config.build().toConfig() ) )
        {
            // Given
            Session session = driver.session();

            // Test for base case default : Binary Cosine Similarity
            Value result= session.run( "RETURN statfunctions.BinarySimilarity([1, 2], ['a', 'b'], 'All' ) AS result").single().get("result");
            assertThat( result.get("Jaccard").asDouble(), equalTo(0.0));
            assertThat( result.get("Cosine").asDouble(), equalTo(0.0));
            assertThat( result.get("Euclid").asDouble(), equalTo(2.0));
            assertThat( result.get("Manhattan").asDouble(), equalTo(4.0));
            assertThat( result.get("Dice").asDouble(), equalTo(0.0));

        }
    }
}