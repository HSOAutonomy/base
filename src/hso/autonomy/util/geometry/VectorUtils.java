/* Copyright 2008 - 2017 Hochschule Offenburg
 * For a list of authors see README.md
 * This software of HSOAutonomy is released under MIT License (see LICENSE).
 */
package hso.autonomy.util.geometry;

import java.util.Arrays;
import java.util.List;
import java.util.function.ToDoubleFunction;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class VectorUtils
{
	public static Vector3D to3D(Vector2D vector2D)
	{
		return new Vector3D(vector2D.getX(), vector2D.getY(), 0);
	}

	public static Vector2D to2D(Vector3D vector3D)
	{
		return new Vector2D(vector3D.getX(), vector3D.getY());
	}

	public static Vector3D average(List<Vector3D> vectors)
	{
		return average(vectors.toArray(new Vector3D[0]));
	}

	public static Vector3D average(Vector3D... vectors)
	{
		double x = average(vectors, Vector3D::getX);
		double y = average(vectors, Vector3D::getY);
		double z = average(vectors, Vector3D::getZ);
		return new Vector3D(x, y, z);
	}

	private static <T> double average(Vector3D[] vectors, ToDoubleFunction<? super Vector3D> mapper)
	{
		return Arrays.stream(vectors).mapToDouble(mapper).average().getAsDouble();
	}

	public static Vector3D flipXY(Vector3D v)
	{
		return new Vector3D(v.getX() * -1, v.getY() * -1, v.getZ());
	}

	public static double getDistanceBetweenXY(Vector3D a, Vector3D b)
	{
		Vector3D delta = a.subtract(b);
		return new Vector3D(delta.getX(), delta.getY(), 0).getNorm();
	}

	public static Angle getDirectionTo(Vector3D a, Vector3D b)
	{
		return Angle.rad(b.subtract(a).getAlpha());
	}
}