package domain.SaveLoadPackage;

import java.io.Serializable;

import domain.SpherePackage.EnchantedSphere;

public class SerializableSphere implements Serializable {
	private EnchantedSphere sphereDomain;

	public EnchantedSphere getSphereDomain() {
		return sphereDomain;
	}

	public SerializableSphere(EnchantedSphere sphereDomain) {
		this.sphereDomain = sphereDomain;
	}
}
