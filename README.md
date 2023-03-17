# RR-and-SRTF-Schedueling
For the RR algorithm, the program takes input for the processes info and the quantum time. The input is put in adjacent arrays. The algorithm then passes through a preemtive loop for each process. After the quantum time is reduced to zero at each iteration, the loop shifts the attention to the next proccess in the array. The algorithm continues until the processes reach 0.
![image](https://user-images.githubusercontent.com/128186479/226062501-2af68b9f-c817-43a0-81e7-a0cbef1a2dc0.png)

For the SRTF, process info is preset and isn't taken as input. An object process is created for each process with the provided data. As for the algorithm itself, the algorithm keeps track of the remaining time for the current process in the processor and all the burst times for the other processes. if the current process's remaining time is bigger than the remaining time for a process in the array then it removes it and places the new process in the algorithm
![image](https://user-images.githubusercontent.com/128186479/226063210-9282d5cc-bb08-49b3-8998-d7b3b92e7900.png)
