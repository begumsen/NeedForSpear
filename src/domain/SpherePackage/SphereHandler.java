package domain.SpherePackage;

import java.io.Serializable;

public class SphereHandler implements Serializable {
	public static EnchantedSphere sphere;

	public SphereHandler(EnchantedSphere sphereDomain) {
		sphere = sphereDomain;
	}

	public void addListener(ISphereListener lis) {
		sphere.addSphereListener(lis);
	}
}
