package dragapult

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import java.io.File

class PlatformFileDescriptorFileGuardTest {

    private lateinit var parentDir: File
    private lateinit var descriptor: PlatformFileDescriptorFileGuard

    @Mock
    private lateinit var source: PlatformFileDescriptor

    @BeforeEach
    fun prepare() {
        MockitoAnnotations.openMocks(this).close()
        descriptor = PlatformFileDescriptorFileGuard(source)
        parentDir = File("/tmp/dragapult/")
        parentDir.mkdirs()
    }

    @AfterEach
    fun tearDown() {
        parentDir.deleteRecursively()
    }

    @Test
    fun `creates new file`() {
        val childFile = File(parentDir, "test.txt")
        whenever(source.getFile(parentDir)).thenReturn(childFile)
        descriptor.getFile(parentDir)
        assertThat(childFile.exists()).isTrue()
        assertThat(childFile.isFile).isTrue()
    }

    @Test
    fun `deletes directory and creates new file instead`() {
        val childFile = File(parentDir, "test.txt")
        childFile.mkdirs()
        whenever(source.getFile(parentDir)).thenReturn(childFile)
        descriptor.getFile(parentDir)
        assertThat(childFile.exists()).isTrue()
        assertThat(childFile.isFile).isTrue()
    }

}