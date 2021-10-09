###### tags: `類神經網路作業`
# Hopfield

1. 程式執行說明:
   執行檔**Hopfield.jar**要用命令題是字元開啟，並且要先下載JavaFX:
>    java --module-path "C:\javafx-sdk-11.0.2\lib" --add-modules javafx.controls,javafx.fxml -jar Hopfield.jar
   
2. 執行介面說明:

   ![](https://i.imgur.com/EsS7aJe.png)
    * 上方:
        * File Name 會根據所選擇的檔案顯示為Basic或Bonus。
        * 點選Basic或Bonus選擇要進行回想的檔案。
        * Learning time 可調整疊代次數，初始為1，最大為30。
        * 是否勾選1/p，可選擇在計算W時，是否要加入1/p。
        * 勾選θ=0，閥值θ_j=0。
        * 勾選 no same ，在計算鍵結值時，重複的輸入只會計算一次。
    * 中間:
    	* Train:輸入的訓練資料。
    	* Test:記憶提取階段的測試資料。
    	* Output:最後的輸出結果。
    * 右邊:
        * 點選Modify會顯示出可修改測試資料的介面，可直接修改資料當作輸入，但每筆資料間必須要空2行，且要記得補空白。
        * 勾選Modify，則會根據修改的資料進行回想。

        ![](https://i.imgur.com/64fLcK3.png)
	
3. 程式碼簡介:
   程式用Java編寫，使用JavaFX與scene builder的GUI完成最後結果。共分成兩個檔案，Hopfield 和Controller。Hopfield 為開啟GUI介面的主程式。後面詳細介紹Controller的程式碼。
   * **onSliderChanged_learning_times(),_Choose_Bonus(),_Choose_Basic, _show_modify,_modify_invisable**: 皆為控制GUI上各個按鍵及控制器的功能。
   * **calculate()**:
        * 讀檔: (input_file) 程式主要的運算，先讀檔案，把讀到的資料存起來並計算每個輸入有多少個資料。
        * 建鍵結值矩陣: 建鍵結值矩陣，並在此判斷是否有重複的輸入及是否加入1/p，根據使用者選擇的結果決定是否加進矩陣運算。
        * 設定θ值: 根據使用者決定是否為0。
        * 記憶提取:(sgn_function()) 計算測試資料的輸出。
        * 輸出:(print_char()) 最後將測試、訓練及最後迴響的結果輸出到GUI上。
    * **sgn_function()**: 判斷輸出1,-1,或 x_j (n)。
    * **input_file()**: 看讀到的檔案為1或空白，若為1存1，為空白存-1，以方便之後運算。並計算輸入的資料維度及數目。
    * **check_same()**: 判斷是否有輸入兩筆相同的資料。
    * **print_char()**: 印出所有的輸入輸出。
