
class SolutionBasicTest1 { 
    static void minMaxSum (int[] arr) {

    // Inisialisasi min, max dan jumlahkan dengan 0.
    int min = arr[0] , max =arr[0], sum = 0;

    for(int i = 0; i < arr.length; i++) {
        //Menghitung jumlah dari array
        sum += arr[i];

        // Update elemen minimum
        if(arr[i] < min) {
            min = arr[i];
        }
        // Update elemen maksimum
        if(arr[i] > max) {
            max = arr[i];
        }
    }
    // Print jumlah minimum dan maksimum
    System.out.println((sum - max) + " " + (sum - min));

}
    public static void main(String[] args) {
    
        int case1[] = {1,3,5,7,9};
        minMaxSum(case1);

        int case2[] = {1,2,3,4,5};
        minMaxSum(case2);
    }    
}
