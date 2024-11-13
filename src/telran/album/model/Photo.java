package telran.album.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Photo {
    // Поля, представляющие данные о фотографии
    private int albumId;       // Идентификатор альбома
    private int photoId;       // Идентификатор фотографии
    private String title;      // Название фотографии
    private String url;        // URL фотографии
    private LocalDateTime date; // Дата создания фотографии

    // Конструктор, инициализирующий все поля
    public Photo(int albumId, int photoId, String title, String url, LocalDateTime date) {
        this.albumId = albumId;
        this.photoId = photoId;
        this.title = title;
        this.url = url;
        this.date = date;
    }

    // Геттеры для каждого поля
    public int getAlbumId() { return albumId; }
    public int getPhotoId() { return photoId; }
    public String getTitle() { return title; }
    public String getUrl() { return url; }
    public LocalDateTime getDate() { return date; }

    // Сеттеры для изменения URL и названия
    public void setUrl(String url) { this.url = url; }
    public void setTitle(String title) { this.title = title; }

    // Метод для красивого представления объекта в виде строки
    @Override
    public String toString() {
        return "Photo{" +
                "albumId=" + albumId +
                ", photoId=" + photoId +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", date=" + date +
                '}';
    }

    // Переопределение equals для сравнения объектов по полям photoId и albumId
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Проверка на совпадение ссылок
        if (o == null || getClass() != o.getClass()) return false; // Проверка класса
        Photo photo = (Photo) o;
        return photoId == photo.photoId && albumId == photo.albumId; // Сравнение по photoId и albumId
    }

    // Переопределение hashCode для обеспечения корректного использования в коллекциях
    @Override
    public int hashCode() {
        return Objects.hash(photoId, albumId);
    }
}

