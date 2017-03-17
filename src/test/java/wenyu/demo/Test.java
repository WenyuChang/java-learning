package wenyu.demo;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	
	public static void main(String[] args) {
		String line = "cassandra.staging.sntc3x.cass-3xtest-sntc-n111.org.apache.cassandra.metrics.Table.SnapshotsSize.system_auth.roles";
		String pattern = "cassandra.*.*.*.org.apache.cassandra.metrics.Table.*";


		line = "cassandra.staging.sntc3x.cass-3xtest-sntc-n111.org.apache.cassandra.metrics.keyspace.SnapshotsSize.system_auth.roles";
		pattern = "cassandra\\..*org.apache.cassandra.metrics.keyspace.*";


		if (line.matches(pattern)) {
			System.out.println("Found value");
		}else {
			System.out.println("NO MATCH");
		}
	}
}