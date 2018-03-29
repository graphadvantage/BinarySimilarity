# BinarySimilarity


Calculate Binary Similarity fiven two collection vectors and any of 5 similarity functions : 
"Jaccard", "Cosine", "Euclid", "Manhattan", "Dice" 
(Default to Cosine similarity)
     
example : 
statsfunctions.BinarySimilarity([2, 3], [1, 2], 'Jaccard' )  
statsfunctions.BinarySimilarity(['a', 'b'], ['a', 'a' , 'b', 'b', 'b', 'b' ], 'Dice' )            
statsfunctions.BinarySimilarity([1,2,3,4], [1,2,3,4])  // Calculate Binary Cosine Similarity by default 

