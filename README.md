"# benchmark-jmh" 

For testing
```
git clone https://github.com/venergiac/benchmark-jmh.git
mvn install
java -jar target\benchmark-0.0.1-SNAPSHOT.jar
```

Results on a Windows PC with java 9
```
Benchmark      (max)   (p)  Mode  Cnt   Score    Error  Units
MyTest.test1  100000     1  avgt   20  24.751 ±  4.780  ms/op
MyTest.test1  100000   100  avgt   20  25.986 ±  6.268  ms/op
MyTest.test1  100000  1000  avgt   20  32.744 ±  7.484  ms/op
MyTest.test2  100000     1  avgt   20   9.150 ±  0.604  ms/op
MyTest.test2  100000   100  avgt   20   9.951 ±  2.425  ms/op
MyTest.test2  100000  1000  avgt   20   9.231 ±  0.905  ms/op
MyTest.test3  100000     1  avgt   20  34.440 ±  9.014  ms/op
MyTest.test3  100000   100  avgt   20  36.710 ±  8.946  ms/op
MyTest.test3  100000  1000  avgt   20  33.137 ±  9.326  ms/op
MyTest.test4  100000     1  avgt   20  39.412 ± 10.397  ms/op
MyTest.test4  100000   100  avgt   20  37.823 ± 12.362  ms/op
MyTest.test4  100000  1000  avgt   20  32.411 ±  5.971  ms/op
```

Throughput
```
Benchmark      (max)   (p)   Mode  Cnt  Score   Error   Units
MyTest.test1  100000     1  thrpt   20  0.032 ± 0.009  ops/ms
MyTest.test1  100000   100  thrpt   20  0.040 ± 0.010  ops/ms
MyTest.test1  100000  1000  thrpt   20  0.038 ± 0.010  ops/ms
MyTest.test2  100000     1  thrpt   20  0.113 ± 0.005  ops/ms
MyTest.test2  100000   100  thrpt   20  0.105 ± 0.014  ops/ms
MyTest.test2  100000  1000  thrpt   20  0.111 ± 0.006  ops/ms
MyTest.test3  100000     1  thrpt   20  0.030 ± 0.008  ops/ms
MyTest.test3  100000   100  thrpt   20  0.026 ± 0.007  ops/ms
MyTest.test3  100000  1000  thrpt   20  0.033 ± 0.008  ops/ms
MyTest.test4  100000     1  thrpt   20  0.032 ± 0.005  ops/ms
MyTest.test4  100000   100  thrpt   20  0.030 ± 0.006  ops/ms
MyTest.test4  100000  1000  thrpt   20  0.029 ± 0.006  ops/ms
```

and the winner is test 2!