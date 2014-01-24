# -*- coding:utf-8 -*-
import os

def remove_meta_dir(meta_dir):
    """
        remove meta dir
    """
    abs_git_dir = os.path.join(meta_dir, '.git')
    abs_project_dir = os.path.join(meta_dir, '.project')
    abs_setting_dir = os.path.join(meta_dir, '.settings')

    print 'remove git dir:%s' %  abs_git_dir
    os.popen('rm -rf %s' % abs_git_dir).read()

    print 'remove project dir:%s' %  abs_project_dir
    os.popen('rm -rf %s' % abs_project_dir).read()

    print 'remove setting dir:%s' %  abs_setting_dir
    os.popen('rm -rf %s' % abs_setting_dir).read()


def remove_target_files(topdir):
    """
        remove tempory build files in [target]
    """
    print 'begin to remove unused [target] dirs'
    os.popen('find %s -name "target" | xargs rm -rf' % topdir).read()


def rename_file_content(topdir, src, dst):
    """
        rename file content from [src] to [dst]
        recurively
    """

    print 'begin rename file content from %s to %s' % (src, dst)
    backup_file_ext = 'xxx_sed_backup_file_001'
    os.popen('find %s -type file | xargs sed -i %s -e "s/%s/%s/g"' % (topdir, backup_file_ext, src, dst)).read()
    #mac OSX does not support sed without backup
    #so we remove those
    os.popen('find %s -name "*%s" | xargs rm -f' % (topdir, backup_file_ext)).read()


def rename_dir(top_dir, src, dst):
    """
        rename dir from [src] to [dst] with top_dir as base dir
        recursively
    """

    print 'begin rename dir from %s to %s' % (src, dst)
    def rename_filename(dirname, filename, src, dst):
        newfilename = filename.replace(src, dst)
        if newfilename != filename:
            abs_old_filename = os.path.join(dirname, filename)
            abs_new_filename = os.path.join(dirname, newfilename)
            print 'rename [%s] to [%s]' % (abs_old_filename, abs_new_filename)
            os.rename(abs_old_filename, abs_new_filename)
            return newfilename
        return None 

    for root, dirs, files in os.walk(top_dir):
        #change filename first
        for filename in files:
            rename_filename(root, filename, src, dst)

        #change dir name second
        for idx in range(len(dirs)):
            new_dir_name = rename_filename(root, dirs[idx], src, dst)
            if new_dir_name:
                dirs[idx] = new_dir_name


if __name__ == '__main__':
    import sys
    if len(sys.argv) != 4:
        print 'python correct_project.py topdir src dst'
        print 'example:'
        print ' python correct_project.py /Users/yancl/Work/java/gs-favorite favorite favorite'
        print ' python correct_project.py /Users/yancl/Work/java/gs-favorite Favorite Favorite'
        sys.exit(1)

    topdir = sys.argv[1]
    src = sys.argv[2]
    dst = sys.argv[3]

    remove_meta_dir(topdir)
    remove_target_files(topdir)
    rename_dir(topdir, src, dst)
    rename_file_content(topdir, src, dst)

    print 'done.'
