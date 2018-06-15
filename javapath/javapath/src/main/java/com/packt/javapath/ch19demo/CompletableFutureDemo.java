package com.packt.javapath.ch19demo;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CompletableFutureDemo {
    private static final List<String> ids = IntStream.range(1, 11)
            .mapToObj(i -> "id" + i).collect(Collectors.toList());
    public static void main(String[] args) {
        CompletableFutureDemo demo = new CompletableFutureDemo();
        demo.averageDemo();
    }

    public void averageDemo() {

        Function<String, Double> mSys = id -> {
            //System.out.println("mSys: Sensor " + id + " is called...");
            pauseMs(100);
            return 10. + Math.random();
        };

        getAverage(() -> collectData(ids.stream(), mSys));    //prints: 10.46 in 1031 ms
        getAverage(() -> collectData(ids.parallelStream(), mSys));  //prints: 10.49 in 212 ms

        Function<String, Double> mSys2 = id -> {
            //System.out.println("mSys2: Sensor " + id + " is called...");
            pauseMs(100);
            return 10. + Math.random();
        };

        Function<String, Double> mSys3 = id -> {
            //System.out.println("mSys3: Sensor " + id + " is called...");
            pauseMs(100);
            return 10. + Math.random();
        };

        List<Function<String, Double>> mSystems = List.of(mSys, mSys2, mSys3);
        List<List<String>> idLists = List.of(ids, ids, ids);

        Map<Integer, List<CompletableFuture<Double>>> requestLists = sendRequests(idLists, mSystems);

        pauseMs(2000);  //The main thread can continue doing something else
                        //for any period of time
        getAverage(requestLists);

    }

    private Map<Integer, List<CompletableFuture<Double>>> sendRequests(List<List<String>> idLists, List<Function<String, Double>> mSystems){
        LocalTime start = LocalTime.now();
        //ExecutorService pool = Executors.newCachedThreadPool();
        Map<Integer, List<CompletableFuture<Double>>> requestLists = new HashMap<>();
        for(int i = 0; i < idLists.size(); i++){
            for(Function<String, Double> mSys: mSystems){
                List<String> ids = idLists.get(i);
                List<CompletableFuture<Double>> list = ids.stream()
                        //.map(id -> CompletableFuture.supplyAsync(() -> mSys.apply(id), pool))
                        .map(id -> CompletableFuture.supplyAsync(() -> mSys.apply(id)))
                        .collect(Collectors.toList());
                requestLists.put(i, list);
            }
        }
        //pool.shutdown();
        long dur = Duration.between(start, LocalTime.now()).toMillis();
        System.out.println("All requests are submitted in " + dur + " ms");
        return requestLists;
    }

    private void getAverage(Map<Integer, List<CompletableFuture<Double>>> requestLists){
        for(List<CompletableFuture<Double>> list: requestLists.values()){
            getAverage(() -> list.stream().map(CompletableFuture::join));
        }
    }

    private Stream<Double> collectData(Stream<String> stream, Function<String, Double> mSys){
        return  stream.map(id -> mSys.apply(id));
    }

    private void getAverage(Supplier<Stream<Double>> collectData) {
        LocalTime start = LocalTime.now();
        double a = collectData.get().mapToDouble(Double::valueOf).average().orElse(0);
        System.out.println((Math.round(a * 100.) / 100.) + " in " + Duration.between(start, LocalTime.now()).toMillis() + " ms");
    }


    private interface MeasuringSystem {
        double get(String id);
    }
    private void pauseMs(int ms) {
        try{
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch(InterruptedException ex){
            ex.printStackTrace();
        }
    }

}
