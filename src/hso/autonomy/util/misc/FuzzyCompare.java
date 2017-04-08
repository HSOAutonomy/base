/* Copyright 2008 - 2017 Hochschule Offenburg
 * For a list of authors see README.md
 * This software of HSOAutonomy is released under MIT License (see LICENSE).
 */

package hso.autonomy.util.misc;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * Implements a set of fuzzy compare operators for float and double data types.
 *
 * @author Ingo Schindler
 */
public class FuzzyCompare
{
	/**
	 * Fuzzy "equal" ( == ) comparison
	 *
	 * @param reference Reference value
	 * @param compare Value to be compared to the reference
	 * @param range The range within the two values are equal
	 * @return true if the both values are within the same range
	 */
	public static boolean eq(float reference, float compare, float range)
	{
		if (reference > compare) {
			return reference - range <= compare;
		}

		return compare - range <= reference;
	}

	/**
	 * Fuzzy "equal" ( == ) comparison
	 *
	 * @param reference Reference value
	 * @param compare Value to be compared to the reference
	 * @param range The range within the two values are equal
	 * @return true if the both values are within the same range
	 */
	public static boolean eq(double reference, double compare, double range)
	{
		if (reference > compare) {
			return reference - range <= compare;
		}

		return compare - range <= reference;
	}

	/**
	 * Fuzzy "Equal" ( == ) comparison for vectors
	 *
	 * @param reference Reference value
	 * @param compare Value to be compared to the reference
	 * @param range The range within the vector values are considered to be equal
	 * @return True if both vectors are equal
	 */
	public static boolean eq(Vector3D reference, Vector3D compare, double range)
	{
		if (reference == null && compare == null) {
			return true;
		}
		if (reference == null || compare == null) {
			return false;
		}

		if (!eq(reference.getX(), compare.getX(), range)) {
			return false;
		}
		if (!eq(reference.getY(), compare.getY(), range)) {
			return false;
		}
		return eq(reference.getZ(), compare.getZ(), range);
	}

	/**
	 * Fuzzy "Greater Than or Equal" ( >= ) comparison
	 *
	 * @param reference Reference value
	 * @param compare Value to be compared to the reference
	 * @param range The range within the two values are considered equal
	 * @return True if greater or equal, false if not
	 */
	public static boolean gte(float reference, float compare, float range)
	{
		return eq(reference, compare, range) || reference > compare;
	}

	/**
	 * Fuzzy "Greater Than or Equal" ( >= ) comparison
	 *
	 * @param reference Reference value
	 * @param compare Value to be compared to the reference
	 * @param range The range within the two values are considered equal
	 * @return True if greater or equal, false if not
	 */
	public static boolean gte(double reference, double compare, double range)
	{
		return eq(reference, compare, range) || reference > compare;
	}

	/**
	 * Fuzzy "Lower Than or Equal" ( <= ) comparison
	 *
	 * @param reference Reference value
	 * @param compare Value to be compared to the reference
	 * @param range The range within the two values are considered equal
	 * @return True if less than or equal, false if not
	 */
	public static boolean lte(float reference, float compare, float range)
	{
		return gte(compare, reference, range);
	}

	/**
	 * Fuzzy "Lower Than or Equal" ( <= ) comparison
	 *
	 * @param reference Reference value
	 * @param compare Value to be compared to the reference
	 * @param range The range within the two values are considered equal
	 * @return True if less than or equal, false if not
	 */
	public static boolean lte(double reference, double compare, double range)
	{
		return gte(compare, reference, range);
	}

	/**
	 * Fuzzy "Greater Than" ( > ) comparison
	 *
	 * @param reference Reference value
	 * @param compare the lower of two values
	 * @param range The range within the two values are considered equal
	 * @return true if the first parameter is greater than the second one and the
	 *         second one is not within the range of the first value
	 */
	public static boolean gt(float reference, float compare, float range)
	{
		return gte(reference, compare, range);
	}

	/**
	 * Fuzzy "Greater Than" ( > ) comparison
	 *
	 * @param reference Reference value
	 * @param compare the lower of two values
	 * @param range The range within the two values are considered equal
	 * @return true if the first parameter is greater than the second one and the
	 *         second one is not within the range of the first value
	 */
	public static boolean gt(double reference, double compare, double range)
	{
		return gte(reference, compare, range);
	}

	/**
	 * Fuzzy "Lower Than" ( < ) comparison
	 *
	 * @param reference Reference value
	 * @param compare Value to be compared to the reference
	 * @param range The range within the two values are considered equal
	 * @return True if lower, false if not
	 */
	public static boolean lt(float reference, float compare, float range)
	{
		return gte(compare, reference, range);
	}

	/**
	 * Fuzzy "Lower Than" ( < ) comparison
	 *
	 * @param reference Reference value
	 * @param compare Value to be compared to the reference
	 * @param range The range within the two values are considered equal
	 * @return True if lower, false if not
	 */
	public static boolean lt(double reference, double compare, double range)
	{
		return gte(compare, reference, range);
	}
}
