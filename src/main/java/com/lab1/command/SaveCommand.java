package com.lab1.command;

import com.lab1.model.HTML;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveCommand implements Command {
    private String url;
    private HTML html;

    //    进行必要的异常检测，例如提供的路径名无法写入文件。
    @Override
    public void execute() {
        html.save(url);
    }
}
