package day06lecture;

@FunctionalInterface
public interface MyRunnableInterface<T> {

    T process(T a, T b);
    
    
}
