package telran.album;

import telran.album.dao.Album;
import telran.album.model.Photo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AlbumImpl implements Album {
    // Список для хранения фотографий в альбоме
    private List<Photo> photos;

    // Конструктор с параметром, задающим начальную вместимость
    public AlbumImpl(int capacity) {
        this.photos = new ArrayList<>(capacity); // Инициализация списка с заданной вместимостью
    }

    // Конструктор без параметров, создающий пустой список
    public AlbumImpl() {
        this.photos = new ArrayList<>(); // Инициализация пустого списка фотографий
    }

    // Метод для добавления фотографии в альбом
    public boolean addPhoto(Photo photo) {
        if (photos.contains(photo)) return false; // Проверка на наличие дублирующей фотографии
        photos.add(photo); // Добавление фото в список
        photos.sort(Comparator.comparing(Photo::getDate)); // Сортировка списка по дате
        return true; // Возвращаем true, если добавление прошло успешно
    }

    // Метод для удаления фотографии по её идентификатору и идентификатору альбома
    public boolean removePhoto(int photoId, int albumId) {
        return photos.removeIf(p -> p.getPhotoId() == photoId && p.getAlbumId() == albumId); // Удаление фото по условию
    }

    // Метод для обновления URL фотографии
    public boolean updatePhoto(int photoId, int albumId, String url) {
        for (Photo photo : photos) {
            if (photo.getPhotoId() == photoId && photo.getAlbumId() == albumId) { // Поиск фотографии по идентификаторам
                photo.setUrl(url); // Обновление URL
                return true; // Возвращаем true, если обновление прошло успешно
            }
        }
        return false; // Возвращаем false, если фото с такими идентификаторами не найдено
    }

    // Метод для получения фотографии из альбома по идентификатору
    public Photo getPhotoFromAlbum(int photoId, int albumId) {
        return photos.stream()
                .filter(p -> p.getPhotoId() == photoId && p.getAlbumId() == albumId)
                .findFirst()
                .orElse(null); // Возвращаем фото или null, если не найдено
    }

    // Метод для получения всех фотографий из конкретного альбома
    public List<Photo> getAllPhotoFromAlbum(int albumId) {
        List<Photo> result = new ArrayList<>();
        for (Photo photo : photos) {
            if (photo.getAlbumId() == albumId) { // Проверка по albumId
                result.add(photo); // Добавляем фотографию в результат
            }
        }
        return result; // Возвращаем список фотографий из указанного альбома
    }

    // Метод для получения фотографий в указанном диапазоне дат
    public List<Photo> getPhotoBetweenDate(LocalDate dateFrom, LocalDate dateTo) {
        LocalDateTime dateTimeFrom = dateFrom.atStartOfDay(); // Начало диапазона
        LocalDateTime dateTimeTo = dateTo.atStartOfDay().plusDays(1); // Конец диапазона
        List<Photo> result = new ArrayList<>();
        for (Photo photo : photos) {
            if (!photo.getDate().isBefore(dateTimeFrom) && !photo.getDate().isAfter(dateTimeTo)) { // Проверка попадания в диапазон
                result.add(photo); // Добавляем фотографию в результат
            }
        }
        return result; // Возвращаем список фотографий, попадающих в указанный диапазон
    }

    // Метод для получения текущего размера альбома (количество фотографий)
    public int size() {
        return photos.size(); // Возвращаем количество элементов в списке
    }
}

