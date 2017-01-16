package subrectarea;

/*
    Assume 1 as land present in that particular square block
       and 0 as tree in that particular squaare block.
*/

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class SubRectArea {

    public static void main(String[] args) {
        // TODO code application logic here
        //System.out.println("Enter the size of array:");
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();;
        int[][] arr = new int[N][N];
        SubRectArea ar = new SubRectArea();
        //System.out.println("Enter the elements into array:");
        for (int i = 0; i< N;i++){
            for (int j = 0; j< N;j++){
                arr[i][j] = sc.nextInt();
            }
        }   
        int maxRect = ar.maximum(arr);
        //System.out.println("Area of Maximum Rectangule: " +maxRect);
        System.out.println(maxRect);
    }

    private int maximum(int[][] arr) {        
       
        int temp[] = new int[arr[0].length];
        SubRectArea ar = new SubRectArea();
        int maxArea = 0;
        int area = 0;
        for(int i=0; i < arr.length; i++){
            for(int j=0; j < temp.length; j++){
                if(arr[i][j] == 0){
                    temp[j] = 0;
                }else{
                    temp[j] += arr[i][j];
                }
            }
            area = ar.maxHistogram(temp);
            if(area > maxArea){
                maxArea = area;
            }
        }
        return maxArea;
    }
    
    public int maxHistogram(int temp[]){
        Deque<Integer> stack;
        stack = new LinkedList<>();
        int maxArea = 0;
        int i, area;
        for(i=0; i < temp.length;){
            if(stack.isEmpty() || temp[stack.peekFirst()] <= temp[i]){
                stack.offerFirst(i++);
            }else{
                int top = stack.pollFirst();
                if(stack.isEmpty()){
                    area = temp[top] * i;
                }
                else{
                    area = temp[top] * (i - stack.peekFirst() - 1);
                }
                if(area > maxArea){
                    maxArea = area;
                }
            }
        }
        while(!stack.isEmpty()){
            int top = stack.pollFirst();
            if(stack.isEmpty()){
                area = temp[top] * i;
            }
            else{
                area = temp[top] * (i - stack.peekFirst() - 1);
            }
        if(area > maxArea){
                maxArea = area;
            }
        }
        return maxArea;
    }   
}