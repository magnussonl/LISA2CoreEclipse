import LISA.Utils.Config.Config;
import LISA.Utils.Config.ConfigFunctions;

public class Test {
	public static void main(String[] args) {
		System.out.println("test with main");
		Config conf = ConfigFunctions.getConfig();
		System.out.println(conf.getIp());
	}
}
