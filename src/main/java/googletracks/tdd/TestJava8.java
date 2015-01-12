package googletracks.tdd;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestJava8 {
public static void main(String[] args) {
		
		
		// Recuperando o primeiro da lista.
		Arrays.asList("a1", "a2", "a3")
	    .stream()
	    .findFirst()
	    .ifPresent(System.out::println);  // a1
		
		Stream.of("a1", "a2", "a3")
	    .findFirst()
	    .ifPresent(System.out::println);  // a1
		
		
		//Fazendo forEach no IO
		IntStream.range(1, 4).forEach(System.out::println);
		
		//Concatenando string pela lista
		IntStream.range(1, 4)
	    .mapToObj(i -> "a" + i)
	    .forEach(System.out::println);
		
		
		
	}
}
