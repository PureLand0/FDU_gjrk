package com.lab1.command;

import com.lab1.model.HTML;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadCommand implements Command {
    private String url;
    //管理的html文档
    private HTML html;

    //进行必要的异常检测，例如读取的文件不存在。
    @Override
    public void execute() {
        html.read(url);
    }
}

