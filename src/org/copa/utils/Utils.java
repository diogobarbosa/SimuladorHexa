package org.copa.utils;

public class Utils {

	public static int fatorial(int x) {
	    // Se x for igual a 0 (zero) então retorna 1.
	    if (x == 0)
	      return 1;
	        
	    /* Para qualquer outro número, calcula o seu valor multiplicado
	       pelo fatorial de seu antecessor. */
	    return x * fatorial(x - 1);
	  }
}
