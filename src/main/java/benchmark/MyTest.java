package benchmark;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Warmup(iterations = 1, time = 32, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
public class MyTest {

	public static void main(String[] args) throws Exception {
		Main.main(args);
	}

	//@Param({ "1", "20", "30",  "60", "100" })
	@Param({ "1", "100", "1000" })
	int p;

	@Param({ "100000" })
	int max;

	
	HashMap<String, Set<String>> bindings = null;
	TreeMap<String, HashSet> bindings2 = null;

	@Setup
	public void init1() {
		bindings = new HashMap<>();
		for (int i = max; i >= 0; i--) {
			bindings.put("association" + i + ".association2.propertyInAssociation2", new HashSet<>());
			bindings.get("association" + i + ".association2.propertyInAssociation2").add("property1");
			bindings.get("association" + i + ".association2.propertyInAssociation2").add("property2");
			bindings.get("association" + i + ".association2.propertyInAssociation2").add("property3");

			bindings.put("association" + i + ".association2.association3.propertyInAssociation3", new HashSet<>());
			bindings.get("association" + i + ".association2.association3.propertyInAssociation3").add("property4");
			bindings.get("association" + i + ".association2.association3.propertyInAssociation3").add("property5");
			bindings.get("association" + i + ".association2.association3.propertyInAssociation3").add("property6");
			bindings.get("association" + i + ".association2.association3.propertyInAssociation3").add("property7");
		}

		bindings2 = new TreeMap<String, HashSet>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				if (o1.equals(o2))
					return 0;
				if (o1.startsWith(o2))
					return 1;
				return -1;
			}
		});
		{
			for (int i = max; i >= 0; i--) {
				bindings2.put("association" + i + ".association2.propertyInAssociation2", new HashSet<>());
				bindings2.get("association" + i + ".association2.propertyInAssociation2").add("property1");
				bindings2.get("association" + i + ".association2.propertyInAssociation2").add("property2");
				bindings2.get("association" + i + ".association2.propertyInAssociation2").add("property3");

				bindings2.put("association" + i + ".association2.association3.propertyInAssociation3", new HashSet<>());
				bindings2.get("association" + i + ".association2.association3.propertyInAssociation3").add("property4");
				bindings2.get("association" + i + ".association2.association3.propertyInAssociation3").add("property5");
				bindings2.get("association" + i + ".association2.association3.propertyInAssociation3").add("property6");
				bindings2.get("association" + i + ".association2.association3.propertyInAssociation3").add("property7");
			}
		}

	}

	@Benchmark
	public void test1() {
		String path = "association" + p + ".association2.association3";

		List<Map.Entry<String, Set<String>>> result = bindings.entrySet().stream()
				.filter(e -> e.getKey().contains(path)).collect(Collectors.toList());

	}

	@Benchmark
	public void test2() {
		String path = "association" + p + ".association2.association3";

		List<Map.Entry<String, Set<String>>> result = bindings.entrySet().parallelStream()
				.filter(e -> e.getKey().contains(path)).collect(Collectors.toList());
	}

	@Benchmark
	public void test3() {
		String path = "association" + p + ".association2.association3";

		Object result1 = bindings2.tailMap(path).entrySet().stream().filter(e -> e.getKey().contains(path))
				.collect(Collectors.toList());
	}

	@Benchmark
	public void test4() {
		String path = "association" + p + ".association2.association3";

		Object result1 = bindings2.tailMap(path).entrySet().parallelStream().filter(e -> e.getKey().contains(path))
				.collect(Collectors.toList());
	}

}
