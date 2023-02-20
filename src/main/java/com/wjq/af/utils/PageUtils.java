package com.wjq.af.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjq.af.dto.response.PageDtoResult;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页工具类
 *
 * @author yixihan
 * @date 2023/2/15 15:14
 */
@SuppressWarnings("all")
public class PageUtils {
    
    /**
     * {@code Page<K> -> Page<V>}
     *
     * @param K      原始实体类
     * @param V      目标实体类
     * @param page   要转换的{@link Page}分页对象
     * @param mapper 实体类转换方法
     * @return {@link Page}
     */
    public static <K, V> Page<V> convertTo(Page<K> page, Function<? super K, ? extends V> mapper) {
        Page<V> newPage = new Page<> ();
        BeanUtil.copyProperties (page, newPage, getCopyOption ());
        newPage.setRecords (page.getRecords ().stream ().map (mapper).collect (Collectors.toList ()));
        return newPage;
    }
    
    /**
     * {@code IPage<K> -> Page<V>}
     *
     * @param K      原始实体类
     * @param V      目标实体类
     * @param page   要转换的{@link IPage}分页对象
     * @param mapper 实体类转换方法
     * @return {@link Page}
     */
    public static <K, V> Page<V> convertTo(IPage<K> page, Function<? super K, ? extends V> mapper) {
        Page<V> newPage = new Page<> ();
        BeanUtil.copyProperties (page, newPage, getCopyOption ());
        newPage.setRecords (page.getRecords ().stream ().map (mapper).collect (Collectors.toList ()));
        return newPage;
    }
    
    /**
     * {@code IPage<T> -> PageDtoResult<T>}
     *
     * @param T    实体类
     * @param page 要转换的{@link IPage}分页对象
     * @return {@link PageDtoResult}
     */
    public static <T> PageDtoResult<T> pageToPageDtoResult(IPage<T> page) {
        return new PageDtoResult<> (page.getCurrent (), page.getTotal (), page.getSize (), page.getPages (), page.getRecords ());
    }
    
    
    /**
     * {@code IPage<T> -> PageDtoResult<R>}
     *
     * @param T       原始实体类
     * @param R       目标实体类
     * @param page    要转换的{@link IPage}分页对象
     * @param convert 实体类转换方法
     * @return {@link PageDtoResult}
     */
    public static <T, R> PageDtoResult<R> pageToPageDtoResult(IPage<T> page, Function<T, R> convert) {
        List<T> records = page.getRecords ();
        List<R> rList = records == null ? null : records.stream ().map (convert).collect (Collectors.toList ());
        return new PageDtoResult<> (page.getCurrent (), page.getTotal (), page.getSize (), page.getPages (), rList);
    }
    
    private static CopyOptions getCopyOption() {
        CopyOptions copyOptions = new CopyOptions ();
        copyOptions.ignoreError ();
        copyOptions.ignoreNullValue ();
        copyOptions.setIgnoreProperties ("records");
        return copyOptions;
    }
}
