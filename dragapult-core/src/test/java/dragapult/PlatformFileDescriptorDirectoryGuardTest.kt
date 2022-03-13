package dragapult

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import java.io.File

class PlatformFileDescriptorDirectoryGuardTest {

    private lateinit var parentDir: File
    private lateinit var descriptor: PlatformFileDescriptorDirectoryGuard

    @Mock
    private lateinit var source: PlatformFileDescriptor

    @BeforeEach
    fun prepare() {
        MockitoAnnotations.openMocks(this).close()
        descriptor = PlatformFileDescriptorDirectoryGuard(source)
        parentDir = File("/tmp/dragapult/")
    }

    @AfterEach
    fun tearDown() {
        parentDir.deleteRecursively()
    }

    @Test
    fun `creates directory`() {
        whenever(source.getFile(parentDir)).thenReturn(parentDir)
        descriptor.getFile(parentDir)
        assertThat(parentDir.exists()).isTrue()
        assertThat(parentDir.isDirectory).isTrue()
    }

    @Test
    fun `creates all parent directories`() {
        val childFile = File(parentDir, "foo/bar/test.txt")
        whenever(source.getFile(parentDir)).thenReturn(childFile)
        descriptor.getFile(parentDir)
        var parent: File = childFile.parentFile
        while (parent != parentDir) {
            assertThat(parent.exists()).isTrue()
            assertThat(parent.isDirectory).isTrue()
            parent = parent.parentFile
        }
    }

}