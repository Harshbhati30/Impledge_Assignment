# Impledge_Assignment

TO RUN 
java WordCompound.java

Make sure these files are present in the same directory:
WordCompound.java
Input_01.txt
Input_02.txt


The program uses a Trie data structure where each insert() and search() operation runs in O(L) time, where L is the length of the word, since we visit exactly one node per character. The compound() function runs in O(L^2) per word because at each of the L character positions, it may call search() which itself takes O(L) time. Words are sorted once before processing which takes O(N log N) time. Overall the total time complexity of the program is O(N × L^2), where N is the total number of words in the file and L is the average word length. Space complexity is O(N × L) for storing all characters across all words in the Trie.


