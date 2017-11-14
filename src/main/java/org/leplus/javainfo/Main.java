package org.leplus.javainfo;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public final class Main {

	private Main() {
		super();
	}
	
	public static void main(String[] args) {

		System.out.println(
				"Application arguments = " + Arrays.asList(args).stream().collect(Collectors.joining(" ", "\"", "\"")));

		final RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
		System.out.println("JVM arguments = "
				+ runtimeMXBean.getInputArguments().stream().collect(Collectors.joining(" ", "\"", "\"")));
		System.out.println("Boot Class Path = " + runtimeMXBean.getBootClassPath());
		System.out.println("Class Path = " + runtimeMXBean.getClassPath());
		System.out.println("Library Path = " + runtimeMXBean.getLibraryPath());
		System.out.println("Management Spec Path = " + runtimeMXBean.getManagementSpecVersion());
		System.out.println("JVM Name = " + runtimeMXBean.getName());
		System.out.println("Spec Name = " + runtimeMXBean.getSpecName());
		System.out.println("Spec Vendor = " + runtimeMXBean.getSpecVendor());
		System.out.println("Spec Version = " + runtimeMXBean.getSpecVersion());
		System.out.println("VM Name = " + runtimeMXBean.getVmName());
		System.out.println("VM Vendor = " + runtimeMXBean.getVmVendor());
		System.out.println("VM Version = " + runtimeMXBean.getVmVersion());

		System.out.println();
		final Runtime runtime = Runtime.getRuntime();
		System.out.println("Runtime.getRuntime().availableProcessors() = " + runtime.availableProcessors());
		System.out.println();
		System.out.println("Runtime.getRuntime().freeMemory() = " + format(runtime.freeMemory()));
		System.out.println("Runtime.getRuntime().totalMemory() = " + format(runtime.totalMemory()));
		final long maxMemory = runtime.maxMemory();
		System.out.println("Runtime.getRuntime().maxMemory() = "
				+ (maxMemory == Long.MAX_VALUE ? "unlimited" : format(maxMemory)));
		
		// Java 9: look at ProcessHandle.current()
		
		final File[] roots = File.listRoots();
		int i = 0;
		for (File root : roots) {
			System.out.println();
			System.out.println("Filesystem " + i++ + ":");
			System.out.println("root.getAbsolutePath() = " + root.getAbsolutePath());
			System.out.println("root.getUsableSpace() = " + format(root.getUsableSpace()));
			System.out.println("root.getFreeSpace() = " + format(root.getFreeSpace()));
			System.out.println("root.getTotalSpace() = " + format(root.getTotalSpace()));
		}

		System.out.println();
		System.out.println("System Properties:");
		for (Map.Entry<Object, Object> v : new TreeMap<Object, Object>(System.getProperties()).entrySet()) {
			System.out.println(v.getKey() + " = \"" + v.getValue() + "\"");
		}
	}

	private static String format(long bytes) {
		final long kbytes = Math.round(bytes / 1000.0);
		final long mbytes = Math.round(bytes / 1000000.0);
		final long gbytes = Math.round(bytes / 1000000000.0);
		return bytes + " B = " + kbytes + " kB = " + mbytes + " MB = " + gbytes + " GB";
	}

}
