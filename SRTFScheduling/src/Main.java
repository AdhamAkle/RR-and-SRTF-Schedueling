public class Main {
    public static void findWaitingTime(Process[] proc, int n, int[] wt)
    {
        int[] rt = new int[n];

        // Copy the burst time into rt[]
        for (int i = 0; i < n; i++)
            rt[i] = proc[i].burst;

        int complete = 0, t = 0, minm = Integer.MAX_VALUE;
        int shortest = 0, finish_time;
        boolean check = false;

        // Process until all processes gets
        // completed
        while (complete != n) {

            // Find process with minimum
            // remaining time among the
            // processes that arrives till the
            // current time`
            for (int j = 0; j < n; j++)
            {
                if ((proc[j].arrival <= t) &&
                        (rt[j] < minm) && rt[j] > 0) {
                    minm = rt[j];
                    shortest = j;
                    check = true;
                }
            }

            if (!check) {
                t++;
                continue;
            }

            // Reduce remaining time by one
            rt[shortest]--;

            // Update minimum
            minm = rt[shortest];
            if (minm == 0)
                minm = Integer.MAX_VALUE;

            // If a process gets completely
            // executed
            if (rt[shortest] == 0) {

                // Increment complete
                complete++;
                check = false;

                // Find finish time of current
                // process
                finish_time = t + 1;

                // Calculate waiting time
                wt[shortest] = finish_time -
                        proc[shortest].burst -
                        proc[shortest].arrival;

                if (wt[shortest] < 0)
                    wt[shortest] = 0;
            }
            // Increment time
            t++;
        }
    }

    // Method to calculate turn around time
    static void findTurnAroundTime(Process[] proc, int n,
                                   int[] wt, int[] tat)
    {
        // calculating turnaround time by adding
        // bt[i] + wt[i]
        for (int i = 0; i < n; i++)
            tat[i] = proc[i].burst + wt[i];
    }

    // Method to calculate average time
    static void findavgTime(Process[] proc, int n)
    {
        int[] wt = new int[n], tat = new int[n];
        int  total_wt = 0, total_tat = 0;

        // Function to find waiting time of all
        // processes
        findWaitingTime(proc, n, wt);

        // Function to find turn around time for
        // all processes
        findTurnAroundTime(proc, n, wt, tat);

        // Display processes along with all
        // details
        System.out.println("Processes " +
                " Burst time " +
                " Waiting time " +
                " Turn around time");

        // Calculate total waiting time and
        // total turnaround time
        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.println(" " + proc[i].pid + "\t\t"
                    + proc[i].burst + "\t\t " + wt[i]
                    + "\t\t" + tat[i]);
        }

        System.out.println("Average waiting time = " +
                (double)total_wt / (double)n);
        System.out.println("Average turn around time = " +
                (double)total_tat / (double)n);
    }

    // Driver Method
    public static void main(String[] args)
    {
        Process processes[] = { new Process(1, 20, 1),
                                new Process(2, 3, 1),
                                new Process(3, 9, 2),
                                new Process(4, 12, 3)};
        findavgTime(processes, processes.length);
    }
}