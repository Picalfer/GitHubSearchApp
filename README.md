# GitHubSearchApp
App where you can search users and repos by name, open profile of user or watch files of repo etc.

Несложное приложение для быстрого поиска пользователей и репозиториев по названию / логину, а также для просмотра файлов внутри репозитория.
Весь контент в приложении берется через GitHub API.

В этом приложении вы можете:
1. Искать профили и репозитории
2. По нажатию на пользователя переходить в браузер на его профиль
3. По нажатию на репозиторий откроется список файлов репозитория
4. По нажатию на папку внутри репозитория откроется список файлов внутри выбранной папки
5. Внутри файлов репозитория при нажатии на файл (не папку) откроется webView со странице этого файла на git hub
6. В случае ошибки получения данных из сети вас переведет на экране где вы увидите наименование ошибки

Стек проекта:
1. Kotlin
2. Retrofit2
3. Coroutines
4. RecyclerView
5. ViewModel
6. webView

<img  src="https://github.com/Picalfer/GitHubSearchApp/blob/master/app/src/main/res/drawable/screen_main.webp"  width="350" alt="Landing screen"/> <img  src="https://github.com/Picalfer/GitHubSearchApp/blob/master/app/src/main/res/drawable/screen_load_search.webp"  width="350" alt="Game screen"/>
<img  src="https://github.com/Picalfer/GitHubSearchApp/blob/master/app/src/main/res/drawable/screen_repo_search.webp"  width="350" alt="Landing screen"/> <img  src="https://github.com/Picalfer/GitHubSearchApp/blob/master/app/src/main/res/drawable/screen_result.webp"  width="350" alt="Game screen"/>
<img  src="https://github.com/Picalfer/GitHubSearchApp/blob/master/app/src/main/res/drawable/screen_files.webp"  width="350" alt="Game screen"/> <img  src="https://github.com/Picalfer/GitHubSearchApp/blob/master/app/src/main/res/drawable/screen_view_file.webp"  width="350" alt="Game screen"/>
<img  src="https://github.com/Picalfer/GitHubSearchApp/blob/master/app/src/main/res/drawable/screen_pofile.webp"  width="350" alt="Game screen"/> <img  src="https://github.com/Picalfer/GitHubSearchApp/blob/master/app/src/main/res/drawable/screen_error.webp"  width="350" alt="Game screen"/>
