package telran.album;

import telran.album.model.Photo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AlbumTest {
    private AlbumImpl album;

    // Инициализация объекта AlbumImpl и добавление фотографий перед каждым тестом
    @BeforeEach
    void testSetUp() {
        album = new AlbumImpl(10); // Создаем альбом с вместимостью 10

        // Создаем и добавляем несколько фотографий в альбом для начальных данных
        album.addPhoto(new Photo(1, 101, "Title1", "url1", LocalDateTime.now().minusDays(2)));
        album.addPhoto(new Photo(1, 102, "Title2", "url2", LocalDateTime.now().minusDays(1)));
        album.addPhoto(new Photo(1, 103, "Title3", "url3", LocalDateTime.now()));
    }

    // Тест добавления новой фотографии
    @Test
    void testAddPhoto() {
        Photo photo = new Photo(1, 104, "Title4", "url4", LocalDateTime.now());
        assertTrue(album.addPhoto(photo), "Фотография должна успешно добавляться в альбом");
        assertEquals(4, album.size(), "Размер альбома должен быть равен 4 после добавления фото");
    }

    // Тест удаления фотографии
    @Test
    void testRemovePhoto() {
        assertTrue(album.removePhoto(101, 1), "Фотография должна успешно удаляться из альбома");
        assertEquals(2, album.size(), "Размер альбома должен быть равен 2 после удаления фото");
    }

    // Тест обновления URL фотографии
    @Test
    void testUpdatePhoto() {
        assertTrue(album.updatePhoto(101, 1, "newUrl"), "URL фотографии должен обновиться");
        assertEquals("newUrl", album.getPhotoFromAlbum(101, 1).getUrl(), "URL должен быть обновлен до 'newUrl'");
    }

    // Тест получения фотографии из альбома по идентификатору
    @Test
    void
    testGetPhotoFromAlbum() {
        Photo foundPhoto = album.getPhotoFromAlbum(101, 1);
        assertNotNull(foundPhoto, "Фото должно быть найдено");
        assertEquals(101, foundPhoto.getPhotoId(), "ID найденного фото должен быть 101");
    }

    // Тест получения всех фотографий из альбома
    @Test
    void testGetAllPhotoFromAlbum() {
        assertEquals(3, album.getAllPhotoFromAlbum(1).size(), "Должны быть возвращены все фотографии из альбома");
    }

    // Тест получения фотографий в диапазоне дат
    @Test
    void testGetPhotoBetweenDate() {
        LocalDateTime now = LocalDateTime.now();
        int resultSize = album.getPhotoBetweenDate(now.toLocalDate().minusDays(2), now.toLocalDate()).size();
        assertEquals(3, resultSize, "Должны быть возвращены фотографии в диапазоне дат");
    }

    // Тест получения текущего размера альбома
    @Test
    void testSize() {
        assertEquals(3, album.size(), "Размер альбома должен быть равен количеству добавленных фотографий");
    }
}
