package wenyu.learning;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public abstract class ParentClassBenchMark {
	private ThreadMXBean bean;
	private long startTimeNano;
	private long endTimeNano;

	private void setUp() {
		bean = ManagementFactory.getThreadMXBean();
		startTimeNano = bean.getCurrentThreadCpuTime();
	}
	
	public abstract void doExecut();
	
	private void tearDown(int runtime) {
		bean = ManagementFactory.getThreadMXBean();
		endTimeNano = bean.getCurrentThreadCpuTime();
		System.out.println("================= It costs: " + (endTimeNano-startTimeNano)/runtime + " each time =================");
	}
	
	public static void demoEntry(Class<? extends ParentClassBenchMark> clazz, int runtime) {
		ParentClassBenchMark demo;
		try {
			demo = clazz.newInstance();
			demo.setUp();
			int count = runtime;
			while(count-->0) {
				demo.doExecut();
			}
			demo.tearDown(runtime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
