package com.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 模仿高并发查询耗时任务时，进行多线程调度的访问优化。
 *
 * @author zhengxt
 */
public class MultipleQuery {
    public static void main(String[] args) {

        new Thread() {

            @Override
            public void run() {
                int aa = new MultipleQuery().query("aa");
                System.out.println("aa结果：" + aa);
            }
        }.start();

        new Thread() {

            @Override
            public void run() {
                int aa = new MultipleQuery().query("aa");
                System.out.println("aa2结果：" + aa);
            }
        }.start();

        new Thread() {

            @Override
            public void run() {
                int bb = new MultipleQuery().query("bb");
                System.out.println("bb结果：" + bb);
            }
        }.start();
    }

    /**
     * 在多线程下一定要使用ConcurrentHashMap，HashMap线程不安全。
     */
    static Map<String, List<Integer>> rsMap = new ConcurrentHashMap<>();

    public int query(final String queryParams) {
        return queryInvokeAll(queryParams);
    }

    /**
     * 模拟查询，本次查询有三个线程并发查询
     *
     * @param queryParams
     * @return
     */
    public int queryCountDounLatch(final String queryParams) {
        int rs = 0;
        //对查询条件进行加锁，相同查询条件只允许进行因此查询，其他查询等待缓存加载完毕后再从缓存中读取。
        synchronized (queryParams) {
            List<Integer> list = rsMap.get(queryParams);
            if (list == null) {
                //使用 countdownlatch时多个查询完毕后，再进行结果的处理。
                final CountDownLatch latch = new CountDownLatch(3);
                rsMap.put(queryParams, new ArrayList<Integer>());
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            System.out.println(queryParams + "查询1开始...");
                            Thread.sleep(2000);
                            rsMap.get(queryParams).add(2);
                            latch.countDown();
                            System.out.println(queryParams + "查询1结束，耗时2s");

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            System.out.println(queryParams + "查询2开始...");
                            Thread.sleep(5000);
                            rsMap.get(queryParams).add(5);
                            System.out.println(queryParams + "查询2结束，耗时5s");
                            latch.countDown();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            System.out.println(queryParams + "查询3开始...");
                            Thread.sleep(3000);
                            rsMap.get(queryParams).add(3);
                            System.out.println(queryParams + "查询3结束，耗时3s");
                            latch.countDown();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                try {
                    latch.await(10, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list = rsMap.get(queryParams);
            }
            for (Integer i : list) {
                rs += i;
            }
        }
        return rs;
    }

    /**
     * 使用线程的join方法达到子线程全部完成后再执行父进程逻辑。
     *
     * @param queryParams
     * @return
     */
    public int queryJoin(final String queryParams) {
        int rs = 0;
        //对查询条件进行加锁，相同查询条件只允许进行因此查询，其他查询等待缓存加载完毕后再从缓存中读取。
        synchronized (queryParams) {
            List<Integer> list = rsMap.get(queryParams);
            if (list == null) {
                rsMap.put(queryParams, new ArrayList<Integer>());
                Thread thread1 = new Thread() {
                    @Override
                    public void run() {
                        try {
                            System.out.println(queryParams + "查询1开始...");
                            Thread.sleep(2000);
                            rsMap.get(queryParams).add(2);
                            System.out.println(queryParams + "查询1结束，耗时2s");

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };

                Thread thread2 = new Thread() {
                    @Override
                    public void run() {
                        try {
                            System.out.println(queryParams + "查询2开始...");
                            Thread.sleep(5000);
                            rsMap.get(queryParams).add(5);
                            System.out.println(queryParams + "查询2结束，耗时5s");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };

                Thread thread3 = new Thread() {
                    @Override
                    public void run() {
                        try {
                            System.out.println(queryParams + "查询3开始...");
                            Thread.sleep(3000);
                            rsMap.get(queryParams).add(3);
                            System.out.println(queryParams + "查询3结束，耗时3s");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };

                thread1.start();
                thread2.start();
                thread3.start();
                try {
                    thread1.join();
                    thread2.join();
                    thread3.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list = rsMap.get(queryParams);
            }
            for (Integer i : list) {
                rs += i;
            }
        }
        return rs;
    }

    /**
     * 使用线程池来执行方法，线程池中join方法失效,使用queryCyclicBarrier进行同步。
     *
     * @param queryParams
     * @return
     */
    public int queryCyclicBarrier(final String queryParams) {
        int rs = 0;
        //对查询条件进行加锁，相同查询条件只允许进行因此查询，其他查询等待缓存加载完毕后再从缓存中读取。
        synchronized (queryParams) {
            List<Integer> list = rsMap.get(queryParams);

            ExecutorService executorService = Executors.newFixedThreadPool(3);
            if (list == null) {
                final CyclicBarrier cyclicBarrier = new CyclicBarrier(4);

                rsMap.put(queryParams, new ArrayList<Integer>());
                Thread thread1 = new Thread() {
                    @Override
                    public void run() {
                        try {
                            System.out.println(queryParams + "查询1开始...");
                            Thread.sleep(2000);
                            rsMap.get(queryParams).add(2);
                            System.out.println(queryParams + "查询1结束，耗时2s");
                            cyclicBarrier.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }
                };

                Thread thread2 = new Thread() {
                    @Override
                    public void run() {
                        try {
                            System.out.println(queryParams + "查询2开始...");
                            Thread.sleep(5000);
                            rsMap.get(queryParams).add(5);
                            System.out.println(queryParams + "查询2结束，耗时5s");
                            cyclicBarrier.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }
                };

                Thread thread3 = new Thread() {
                    @Override
                    public void run() {
                        try {
                            System.out.println(queryParams + "查询3开始...");
                            Thread.sleep(3000);
                            rsMap.get(queryParams).add(3);
                            System.out.println(queryParams + "查询3结束，耗时3s");
                            cyclicBarrier.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }
                };

                executorService.execute(thread1);
                executorService.execute(thread2);
                executorService.execute(thread3);


                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                list = rsMap.get(queryParams);
                executorService.shutdown();
            }
            for (Integer i : list) {
                rs += i;
            }
        }
        return rs;
    }


    /**
     * 使用线程池来执行方法，使用invokeAll同步。
     *
     * @param queryParams
     * @return
     */
    public int queryInvokeAll(final String queryParams) {
        //对查询条件进行加锁，相同查询条件只允许进行因此查询，其他查询等待缓存加载完毕后再从缓存中读取。
        synchronized (queryParams) {
            List<Integer> list = rsMap.get(queryParams);
            if (list == null) {
                ExecutorService executorService = Executors.newFixedThreadPool(3);
                rsMap.put(queryParams, new ArrayList<Integer>());
                List<Callable<Integer>> callables = new ArrayList<>();
                callables.add(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        System.out.println(queryParams + "查询1开始...");
                        Thread.sleep(2000);
                        System.out.println(queryParams + "查询1结束，耗时2s");
                        return 2;
                    }
                });

                callables.add(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        System.out.println(queryParams + "查询2开始...");
                        Thread.sleep(5000);
                        System.out.println(queryParams + "查询2结束，耗时5s");
                        return 5;
                    }
                });

                callables.add(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        System.out.println(queryParams + "查询3开始...");
                        Thread.sleep(3000);
                        System.out.println(queryParams + "查询3结束，耗时3s");
                        return 3;
                    }
                });
                try {
                    List<Future<Integer>> futures = executorService.invokeAll(callables);
                    int temp = 0;
                    for (Future<Integer> i : futures) {
                        temp += i.get();
                    }
                    rsMap.get(queryParams).add(temp);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                executorService.shutdown();
            }
        }
        return rsMap.get(queryParams).get(0);
    }

}
