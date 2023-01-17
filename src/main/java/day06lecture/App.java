package day06lecture;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                for(int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + "\tRunnable ..." + i);
                }                
            }
            
        });
        //thread1.start();

        MyRunnableImplementation mRI1 = new MyRunnableImplementation("task 1");
        MyRunnableImplementation mRI2 = new MyRunnableImplementation("task 2");
        MyRunnableImplementation mRI3 = new MyRunnableImplementation("task 3");
        MyRunnableImplementation mRI4 = new MyRunnableImplementation("task 4");
        MyRunnableImplementation mRI5 = new MyRunnableImplementation("task 5");


        // Thread thread2 = new Thread(mRI);
        // thread2.start();

        // Thread thread3 = new Thread(mRI);
        // thread3.start();

        // ExecutorService executorService = Executors.newSingleThreadExecutor();
        // executorService.execute(mRI1);
        // executorService.execute(mRI2);
        // executorService.shutdown();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(mRI1);
        executorService.execute(mRI2);
        executorService.execute(mRI3);
        executorService.execute(mRI4);
        executorService.execute(mRI5);
        executorService.shutdown();

        MyRunnableInterface<Integer> addOperation = (a, b) -> {
            return a + b;
        };
        MyRunnableInterface<Integer> multiplyOperation = (a, b) -> {
            return a * b;
        };
        MyRunnableInterface<Integer> minusOperation = (a, b) -> {
            return a - b;
        };
        MyRunnableInterface<String> concatenation = (a, b) -> {
            return a + b;
        };

        System.out.println("add operation: " + addOperation.process(1, 2));
        System.out.println("multiply operation: " + multiplyOperation.process(3, 7));
        System.out.println("subtraction operation: " + minusOperation.process(3, 14));
        System.out.println("concatenation of strings: " + concatenation.process("Hello, ", "world!"));

        MyMessageInterface printString = (a) -> {
            System.out.println(a);
        };
        printString.printMessage("Testing 2nd interface...");


        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "John", "Smith", 10000));
        employees.add(new Employee(2, "Sally", "Tan", 15000));
        employees.add(new Employee(3, "Mary", "Sue", 20000));
        employees.add(new Employee(4, "Kevin", "Lim", 15000));
        employees.add(new Employee(5, "Adam", "Lee", 7000));

        // employees.forEach(emp -> {
        //     System.out.println(emp);
        // });

        List<Employee> filteredEmployees = employees.stream().filter(emp -> emp.getLastName().contains("x")).collect(Collectors.toList());
        filteredEmployees.forEach(emp -> System.out.println(emp));

        // employees.sort(Comparator.comparing(e -> e.getFirstName()));
        // employees.sort(Comparator.comparing(Employee::getFirstName).reversed());

        Comparator<Employee> compare = Comparator.comparing(e -> e.getFirstName());
        employees.sort(compare);

        // employees.forEach(emp -> {
        //     System.out.println(emp);
        // });

        Comparator<Employee> groupByComparator = Comparator.comparing(Employee::getSalary).thenComparing(Employee::getFirstName);
        employees.sort(groupByComparator);
        employees.forEach(e -> {
            System.out.println(e);
        });


    }
}
