import java.util.*;

public class Main {

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n,quantum, timer = 0, maxPI = 0;
        double avgWait = 0, avgTurn = 0;
        System.out.println("Enter the time quanta : ");
        quantum = s.nextInt();
        System.out.println("Enter the number of processes : ");
        n = s.nextInt();
        int[] arrival = new int[n];
        int[] burst = new int[n];
        int[] wait = new int[n];
        int[] turn = new int[n];
        int[] queue = new int[n];
        int[] currentBurst = new int[n];
        boolean[] complete = new boolean[n];

        System.out.println("Enter the arrival time of the processes: ");
        for(int i = 0; i < n; i++)
            arrival[i] = s.nextInt();

        System.out.println("Enter the burst time of the processes : ");
        for(int i = 0; i < n; i++){
            burst[i] = s.nextInt();
            currentBurst[i] = burst[i];
        }
        Arrays.sort(burst);
        Arrays.sort(currentBurst);
        for(int i = 0; i < n; i++){
            complete[i] = false;
            queue[i] = 0;
        }
        while(timer < arrival[0])
            timer++;
        queue[0] = 1;

        while(true){
            boolean flag = true;
            for(int i = 0; i < n; i++){
                if(currentBurst[i] != 0){
                    flag = false;
                    break;
                }
            }
            if(flag)
                break;
            for(int i = 0; (i < n) && (queue[i] != 0); i++){
                int ctr = 0;
                while((ctr < quantum) && (currentBurst[queue[0]-1] > 0)){
                    currentBurst[queue[0]-1] -= 1;
                    timer += 1;
                    ctr++;
                    checkNewArrival(timer, arrival, n, maxPI, queue);
                }
                if((currentBurst[queue[0]-1] == 0) && (!complete[queue[0] - 1])){
                    turn[queue[0]-1] = timer;
                    complete[queue[0]-1] = true;
                }
                boolean idle = true;
                if(queue[n-1] == 0){
                    for(int k = 0; k < n && queue[k] != 0; k++){
                        if (!complete[queue[k] - 1]) {
                            idle = false;
                            break;
                        }
                    }
                }
                else
                    idle = false;

                if(idle){
                    timer++;
                    checkNewArrival(timer, arrival, n, maxPI, queue);
                }
                queueMaintenance(queue,n);
            }
        }

        for(int i = 0; i < n; i++){
            turn[i] = turn[i] - arrival[i];
            wait[i] = turn[i] - burst[i];
        }

        System.out.print("Program No.\tArrival Time\tBurst Time\tWait Time\tTurnAround Time" + "\n");
        for(int i = 0; i < n; i++){
            System.out.print(i+1+"\t\t"+arrival[i]+"\t\t"+burst[i]
                    +"\t\t"+wait[i]+"\t\t"+turn[i]+ "\n");
        }
        for(int i =0; i< n; i++){
            avgWait += wait[i];
            avgTurn += turn[i];
        }
        System.out.print("\nAverage wait time : "+(avgWait/n)
                +"\nAverage Turn Around Time : "+(avgTurn/n));
    }
    public static void queueUpdate(int[] queue, int timer, int[] arrival, int n, int maxProccessIndex){
        int zeroIndex = -1;
        for(int i = 0; i < n; i++){
            if(queue[i] == 0){
                zeroIndex = i;
                break;
            }
        }
        if(zeroIndex == -1)
            return;
        queue[zeroIndex] = maxProccessIndex + 1;
    }

    public static void checkNewArrival(int timer, int[] arrival, int n, int maxProccessIndex, int[] queue){
        if(timer <= arrival[n-1]){
            boolean newArrival = false;
            for(int j = (maxProccessIndex+1); j < n; j++){
                if(arrival[j] <= timer){
                    if(maxProccessIndex < j){
                        maxProccessIndex = j;
                        newArrival = true;
                    }
                }
            }
            if(newArrival)
                queueUpdate(queue,timer,arrival,n, maxProccessIndex);
        }
    }

    public static void queueMaintenance(int[] queue, int n){

        for(int i = 0; (i < n-1) && (queue[i+1] != 0) ; i++){
            int temp = queue[i];
            queue[i] = queue[i+1];
            queue[i+1] = temp;
        }
    }
}
