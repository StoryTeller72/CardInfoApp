# CardInfoApp
## Визуальное представление работы приложения.

![hippo](http://g.recordit.co/LMkwP3AGQS.gif)

В приложении пытался реализовать архитектуру MVVM, потому что это позволяет в дальнейшем легче поддерживать и масштабировать приложение.
## Локально хранение данных.
Для хранения истории запросов на устройстве была использована библиотека ROOM. Не стал использовать SharedPreferences, потому что у данных сложная структура и их может быть много.
Преимущества этой библиотеки:
1.Эта библиотека работает с SQLite, что позволяет удобно работать с данными, у которых сложная структура с помощью SQL запросов.
2.Удобство работы. Для создания сущностей и запросов используется привычные средства языка Kotlin  с добавлением аннотаций ROOM.
3. В дальнейшем легко добавить новый функционал (добавить функции в интерфейс DAO).

## Работа с сетью
Для работы с сетью была использована библиотека Retrofit. Ее главное преимущество  удобное и гибкое использование. Экономия времени на разработку.
Навигация между фрагментами
Для переключения между фрагментами подключил navigation.
 Google рекомендует не использовать много активити в приложении, а заменить их на фрагменты. Navigation component позволяет легко реализовать переключение между фрагментами.  Приятным дополнением является графическое отображение графа навигации. Так же с помощью плагина SafeArgs можно передавать данные между фрагментами.
## LiveData
В viewModel использовал liveData, потому что она дает следующие преимущества:
1.	Гарантирует, что пользовательский интерфейс соответствует состоянию данных. Поддерживает паттерн “наблюдатель”- обновляет  данные, когда они изменились.
2.	Нет утечек памяти. Наблюдатели привязаны к объектам жизненного цикла, очищаются когда уничтожается жизненный цикл к которому они были привязаны.
3.	Всегда актуальные данные. Поэтому используются во ViewModel.
## Прочее
Для обращения к элементам разметки из кода использовал ViewBinding. 
Для выполнения асинхронных операций использовал корутины, потому что они легковесные и не используют много ресурсов в отличии от создания потоков. К тому же корутины хорошо интегрированы во многие библиотеки.

  
