package com.duelmasters.DuelMastersServer.Service;

import java.util.List;

public class GameEngineUtils {

	// counters
	public static int countTappedInZone(String zone) {

		switch (zone) {
		case "BZ0": {
			break;
		}
		case "MN0": {
			break;
		}
		case "GV0": {
			break;
		}
		default: {
			break;
		}
		}

		return 0;
	}

	public static int countRealmInZone(String zone) {

		switch (zone) {
		case "BZ0": {
			break;
		}
		case "MN0": {
			break;
		}
		case "GV0": {
			break;
		}
		default: {
			break;
		}
		}

		return 0;
	}

	// type = {creature, spell, evolution, blocker (?) }
	public static int countTypeInZone(String zone) {

		switch (zone) {
		case "BZ0": {
			break;
		}
		case "MN0": {
			break;
		}
		case "GV0": {
			break;
		}
		default: {
			break;
		}
		}

		return 0;
	}

	
	// getters with 1 attribute

	public static List<Integer> getRealmInZone(String zone) {

		switch (zone) {
		case "BZ0": {
			break;
		}
		case "MN0": {
			break;
		}
		case "GV0": {
			break;
		}
		default: {
			break;
		}
		}

		return null;
	}

	public static List<Integer> getClassInZone() {
		return null;
	}

	// type = {creature, spell, evolution, blocker (?) }
	public static List<Integer> getTypeInZone() {
		return null;
	}

}
