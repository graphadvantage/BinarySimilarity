# BinarySimilarity


Calculate Binary Similarity fiven two collection vectors and any of 5 similarity functions : 
"Jaccard", "Cosine", "Euclid", "Manhattan", "Dice" 
(Default to Cosine similarity)
     
example : 
statsfunctions.BinarySimilarity([2, 3], [1, 2], 'Jaccard' )  
statsfunctions.BinarySimilarity(['a', 'b'], ['a', 'a' , 'b', 'b', 'b', 'b' ], 'Dice' )            
statsfunctions.BinarySimilarity([1,2,3,4], [1,2,3,4])  // Calculate Binary Cosine Similarity by default 

```
MATCH (p:Person)-[:ACTED_IN]->(m:Movie)
   WITH p, COLLECT(id(m)) AS movies
   WITH COLLECT([p, movies]) AS actors
   UNWIND actors AS a1
   UNWIND actors AS a2
   WITH a1[0].name AS a1, a1[1] AS m1 ,a2[0].name AS a2, a2[1] AS m2, tofloat(statfunctions.BinarySimilarity(a1[1],a2[1]).Cosine) AS similarity
   WHERE a1 <> a2
   RETURN a1,m1,a2,m2,similarity ORDER BY SIZE(m1) DESC,  SIZE(m2) DESC, similarity DESC
```