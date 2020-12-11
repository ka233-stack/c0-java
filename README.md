# miniplc0-java

这里是 miniplc0 实验的 Java 版本。



```
static: 5f 73 74 61 72 74 ('_start')
static: 67 63 64 ('gcd')
static: 6d 61 69 6e ('main')
fn [0] 0 0 -> 0 {
	call 2
}
fn [1] 0 2 -> 1 {
    arga 1
    load.64
    arga 2
    load.64
    cmp.i
    set.gt
    br.false 10
    arga 0
    stackalloc 1
    arga 2
    load.64
    arga 1
    load.64
    call 1
    store.64
    ret
    br 23
    arga 1
    load.64
    push 0
    cmp.i
    br.true 6
    arga 0
    arga 2
    load.64
    store.64
    ret
    br 12
    arga 0
    stackalloc 1
    arga 2
    load.64
    arga 1
    load.64
    sub.i
    arga 1
    load.64
    call 1
    store.64
    ret
    ret
}
fn [2] 3 0 -> 0 {
    loca 0
    push 3
    store.64
    loca 1
    push 35
    store.64
    loca 2
    push 21
    store.64
    loca 0
    load.64
    push 0
    cmp.i
    set.gt
    br.false 15
    stackalloc 1
    loca 1
    load.64
    loca 2
    load.64
    call 1
    print.i
    println
    loca 0
    loca 0
    load.64
    push 1
    sub.i
    store.64
    br -21
    ret
}
```





```
Push(1)
Push(2)
CmpI
SetGt
BrFalse(5)
ArgA(0)
Push(1)
Store64
Ret
Br(14)
Push(3)
Push(4)
CmpI
SetGt
BrFalse(5)
ArgA(0)
Push(2)
Store64
Ret
Br(4)
ArgA(0)
Push(0)
Store64
Ret
Br(0)
```



 

| 0-basic         |      |
| --------------- | :--: |
| ac2-hello-world |  1   |
|                 |      |
|                 |      |
|                 |      |
|                 |      |
|                 |      |
|                 |      |
|                 |      |
|                 |      |





```
let i: int = 2;

fn foo() -> int {
    let i: int = 1;
    return i;
}

fn main() -> void {
}


_startFunc
	_startFunc
	i
	"foo"
	foo
	main
main
	

```

