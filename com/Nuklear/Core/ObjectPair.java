package com.Nuklear.Core;

public class ObjectPair<T1, T2>
{
	private T1 _t1;
	private T2 _t2;

	public ObjectPair(T1 t1, T2 t2)
	{
		_t1 = t1;
		_t2 = t2;
	}

	public T1 getV1()
	{
		return _t1;
	}

	public T2 getV2()
	{
		return _t2;
	}
}
