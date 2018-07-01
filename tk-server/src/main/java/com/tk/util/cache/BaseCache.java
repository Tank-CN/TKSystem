package com.tk.util.cache;

import com.google.common.cache.CacheBuilder;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseCache<K, V> implements Cache<K, V> {

	public final com.google.common.cache.Cache<K, V> cache;

	public BaseCache(long size, long duration) {
		this.cache = CacheBuilder.newBuilder().maximumSize(size).expireAfterWrite(duration, TimeUnit.SECONDS).build();
	}

	public V get(K key) {
		return cache.getIfPresent(key);
	}

	public void put(K key, V value) {
		cache.put(key, value);
	}

	public void remove(K key) {
		Map<K, V> map = cache.asMap();
		map.remove(key);
	};

}
