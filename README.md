# 数字人民币滑动切换效果模仿Demo

## 效果(gif图较大，加载可能较慢)

<img src="imgs/gif1.gif" alt="offset_sc" style="zoom: 25%;" />

## 思路

自定义一个背景View，背景View包含两个子View，通过ViewPager的offset处理背景View的子View绘制，处理对应子View绘制前的clipRect即可。

