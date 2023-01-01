
public class SolutionBasicTest2 {
    static void positifNegatifZero(int[] arr) {

        float positif = 0, negatif = 0, zero = 0;

        for (int x = 0; x < arr.length; x++) {
            if(arr[x] > 0) {
              positif++;  
            } 
            else if (arr[x] < 0) {
                negatif++;
            } 
            else if (arr[x] == 0) {
                zero++;
            }
        }
        System.out.printf("%1.6f\n",positif/arr.length);
        System.out.printf("%1.6f\n",negatif/arr.length);
        System.out.printf("%1.6f\n\n",zero/arr.length);
    }

    public static void main(String[] args) {
        
       //case 1 
       int[] array1 = {2, -1, 5, 6, 0, -3};
       positifNegatifZero(array1); 

       //case 2 
       int[] array2 = {3, 1, -4, 2, 0, 9};
       positifNegatifZero(array2);
    }
}
    
