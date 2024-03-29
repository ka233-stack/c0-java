package miniplc0java.tokenizer;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import miniplc0java.util.Pos;

public class StringIter {

    // 以行为基础的缓冲区
    ArrayList<String> linesBuffer = new ArrayList<>();

    Scanner scanner;

    // 指向下一个要读取的字符
    Pos ptrNext = new Pos(0, 0);

    Pos ptr = new Pos(0, 0);

    boolean initialized = false;

    Optional<Character> peeked = Optional.empty();

    public StringIter(Scanner scanner) {
        this.scanner = scanner;
    }

    // 从这里开始其实是一个基于行号的缓冲区的实现
    // 为了简单起见，我们没有单独拿出一个类实现
    // 核心思想和 C 的文件输入输出类似，就是一个 buffer 加一个指针，有三个细节
    // 1.缓冲区包括 \n
    // 2.指针始终指向下一个要读取的 char
    // 3.行号和列号从 0 开始

    // 一次读入全部内容，并且替换所有换行为 \n
    // 这样其实是不合理的，这里只是简单起见这么实现

    /**
     * 一次性读取全部代码
     * <p>
     * 若已经读取过则直接返回
     * <p>
     * 否则将读取到的行插入列表中
     */
    public void readAll() {
        if (initialized) {
            return;
        }
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            System.out.println(str);
            linesBuffer.add(str + '\n');
        }
        // todo:check read \n?
        initialized = true;
    }

    // 例
    // | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 偏移
    // |---|---|---|---|---|---|---|---|---|---|
    // | h | a | 1 | 9 | 2 | 6 | 0 | 8 | 1 | \n |（缓冲区第0行）
    // | 7 | 1 | 1 | 4 | 5 | 1 | 4 | （缓冲区第1行）
    // 这里假设指针指向第一行的 \n，那么有
    // currentPos() = (0, 9)
    // nextPos() = (1, 0)
    // previousPos() = (0, 8)
    // nextChar() = '\n' 并且指针移动到 (1, 0)
    // peekChar() = '\n' 并且指针不移动

    /**
     * 获取当前字符的位置
     *
     * @return 当前字符的Pos
     */
    public Pos currentPos() {
        return this.ptr;
    }

    /**
     * 获取下一个字符的位置
     */
    public Pos nextPos() {
        // 越界
        if (ptr.row >= linesBuffer.size()) {
            throw new Error("advance after EOF");
        }
        // 换行
        if (ptr.col == linesBuffer.get(ptr.row).length() - 1) {
            return new Pos(ptr.row + 1, 0);
        }
        return new Pos(ptr.row, ptr.col + 1);
    }

    /**
     * 获取上一个字符的位置
     */
    public Pos previousPos() {
        if (this.ptr.row == 0 && this.ptr.col == 0) {
            throw new Error("previous position from beginning");
        }
        if (this.ptr.col == 0) {
            return new Pos(ptr.row - 1, linesBuffer.get(ptr.row - 1).length() - 1);
        }
        return new Pos(ptr.row, ptr.col - 1);
    }

    /**
     * 将指针指向下一个字符，并返回当前字符
     *
     * @return 当前字符
     */
    public char nextChar() {
        char ch;
        if (this.peeked.isPresent()) {
            ch = this.peeked.get();
            this.peeked = Optional.empty();
        } else {
            ch = this.getNextChar();
        }
        this.ptr = this.ptrNext;
        return ch;
    }

    /**
     * 获取下一个字符，并设置ptrNext为下一个字符的位置
     *
     * @return 下一个字符
     */
    private char getNextChar() {
        if (isEOF()) {
            return 0;
        }
        char result = linesBuffer.get(this.ptrNext.row).charAt(this.ptrNext.col);
        ptrNext = nextPos();
        return result;
    }

    /**
     * 查看下一个字符，但不移动指针
     *
     * @return 下一个字符
     */
    public char peekChar() {
        if (this.peeked.isPresent()) {
            return this.peeked.get();
        } else {
            char ch = getNextChar();
            this.peeked = Optional.of(ch);
            return ch;
        }
    }

    public Boolean isEOF() {
        return this.ptr.row >= this.linesBuffer.size();
    }

    // Note: Is it evil to unread a buffer?
    public void unreadLast() {
        ptr = previousPos();
    }
}
